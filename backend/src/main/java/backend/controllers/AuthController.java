package backend.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @PostMapping("/login")
  public String login(@RequestParam String email, @RequestParam String password) {
    // This method can be extended to use JWT or other token-based authentication
    return "Login successful for user: " + email;
  }
}
