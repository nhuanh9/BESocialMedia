package com.example.media.controller;

import com.example.media.model.JwtResponse;
import com.example.media.model.Role;
import com.example.media.model.User;
import com.example.media.model.VerificationToken;
import com.example.media.service.impl.JwtService;
import com.example.media.service.RoleService;
import com.example.media.service.UserService;
import com.example.media.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private Environment env;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<User> create(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Iterable<User> users = userService.findAll();
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user.getUsername())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (!userService.isCorrectConfirmPassword(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (user.getRoles() != null) {
            Role role = roleService.findByName("ROLE_ADMIN");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
        } else {
            Role role1 = roleService.findByName("ROLE_USER");
            Set<Role> roles1 = new HashSet<>();
            roles1.add(role1);
            user.setRoles(roles1);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
        user.setImageUrls("https://firebasestorage.googleapis.com/v0/b/spa-stay.appspot.com/o/img%2F1583085901039?alt=media&token=e396af18-3aa6-49ae-8ffc-22a55124b18a");
        userService.save(user);
        VerificationToken token = new VerificationToken(user);
        token.setExpiryDate(10);
        verificationTokenService.save(token);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Void> confirmUserAccount(@RequestParam("token") String verificationToken) {
        VerificationToken token = verificationTokenService.findByToken(verificationToken);
        if (token != null) {
            boolean isExpired = token.isExpired();
            if (!isExpired) {
                User user = userService.findByEmail(token.getUser().getEmail());
                user.setEnabled(true);
                userService.save(user);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getProfile(@PathVariable Long id) {
        Optional<User> userOptional = this.userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = this.userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(userOptional.get().getId());
        user.setUsername(userOptional.get().getUsername());
        user.setEmail(userOptional.get().getEmail());
        user.setEnabled(userOptional.get().isEnabled());
        user.setPassword(userOptional.get().getPassword());
        user.setRoles(userOptional.get().getRoles());
        user.setConfirmPassword(userOptional.get().getConfirmPassword());

        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/new-password/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<User> updatePassword(@RequestParam("token") String token, @PathVariable Long id, @RequestBody User user) {
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        boolean isExpired = verificationToken.isExpired();
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (isExpired) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!userService.isCorrectConfirmPassword(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String newPassword = passwordEncoder.encode(user.getPassword());
        String confirmPassword = passwordEncoder.encode(user.getConfirmPassword());
        user.setPassword(newPassword);
        user.setConfirmPassword(confirmPassword);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/new-password/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = this.userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(userOptional.get().getId());
        user.setUsername(userOptional.get().getUsername());
        user.setEmail(userOptional.get().getEmail());
        user.setEnabled(userOptional.get().isEnabled());
        user.setRoles(userOptional.get().getRoles());
        user.setFirstName(userOptional.get().getFirstName());
        user.setLastName(userOptional.get().getLastName());
        user.setImageUrls(userOptional.get().getImageUrls());
        user.setGender(userOptional.get().getGender());
        user.setPhoneNumber(userOptional.get().getPhoneNumber());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}