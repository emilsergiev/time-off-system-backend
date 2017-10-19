package com.inveitix.timeoffsystem.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inveitix.timeoffsystem.entities.User;
import com.inveitix.timeoffsystem.models.LoginForm;
import com.inveitix.timeoffsystem.models.RegisterForm;
import com.inveitix.timeoffsystem.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/users")
public class UserController
{
	@Autowired
	UserRepository userRepo;

	@GetMapping(path="/all")
	public Iterable<User> getAllUsers()
	{
		return userRepo.findAll();
	}

	@PostMapping(path="/login")
	public String login(@RequestBody LoginForm form)
	{
		User user = userRepo.getUserByEmail(form.getEmail());

		if (user != null && form.getPassword().equals(user.getPassword()))
		{
			return authorizeUser(user);
		}
		return "invalid username or password";
	}

	@PostMapping(path="/register")
	public Map<String, Set<String>> register(
			@Valid @RequestBody RegisterForm form, BindingResult result)
	{
		Map<String, Set<String>> errors = findRegisterErrors(form, result);

		if (errors.isEmpty()) { registerUser(form); }

		return errors;
	}

	@GetMapping(path="/checkEmail")
	public boolean checkEmail(@RequestParam("email") String email)
	{
		return checkExistingEmail(email);
	}

	@PutMapping(path="/pto/{id}")
	public String updatePto(@RequestBody int pto, @PathVariable long id)
	{
		try
		{
			User user = userRepo.findOne(id);
			user.setPto(pto);
			userRepo.save(user);
		}
		catch (Exception e) { return e.toString(); }
		return "PTO succesfully updated!";
	}

	@DeleteMapping(path="/delete/{id}")
	public String deleteUser(@PathVariable long id)
	{
		try { userRepo.delete(id); }
		catch (Exception e) { return e.toString(); }
		return "User succesfully deleted!";
	}

	private String authorizeUser(User user)
	{
		// TODO some authorization security token etc...
		return "login successfull";
	}

	private void registerUser(RegisterForm form)
	{
		User user = new User();
		user.setName(form.getName());
		user.setEmail(form.getEmail());
		user.setEgn(form.getEgn());
		user.setPassword(form.getPassword());
		userRepo.save(user);
	}

	private Map<String, Set<String>> findRegisterErrors(
			RegisterForm form, BindingResult result)
	{
		Map<String, Set<String>> errors = new HashMap<>();

		if (checkExistingEmail(form.getEmail())) {
			errors.computeIfAbsent("email", key -> new HashSet<>())
			.add("emailTaken");
		}
		else if (!form.getPassword().equals(form.getEgn()))
		{
			errors.computeIfAbsent("passwordConfirm", key -> new HashSet<>())
			.add("nomatch");
		}

		for (FieldError fieldError : result.getFieldErrors())
		{
			String code = fieldError.getCode();
			String field = fieldError.getField();

			if (code.equals("NotBlank"))
			{
				errors.computeIfAbsent(field, key -> new HashSet<>())
				.add("required");
			}
			else if (code.equals("Size") && field.equals("name"))
			{
				if (form.getName().length() < 2)
				{
					errors.computeIfAbsent(field, key -> new HashSet<>())
					.add("minlength");
				}
				else
				{
					errors.computeIfAbsent(field, key -> new HashSet<>())
					.add("maxlength");
				}
			}
			else if (code.equals("Email") && field.equals("email"))
			{
				errors.computeIfAbsent(field, key -> new HashSet<>())
				.add("pattern");
			}
			else if (code.equals("Size") && field.equals("egn"))
			{
				if (form.getEgn().length() < 10)
				{
					errors.computeIfAbsent(field, key -> new HashSet<>())
					.add("minlength");
				}
				else
				{
					errors.computeIfAbsent(field, key -> new HashSet<>())
					.add("maxlength");
				}
			}
			else if (code.equals("Size") && field.equals("password"))
			{
				if (form.getPassword().length() < 6)
				{
					errors.computeIfAbsent(field, key -> new HashSet<>())
					.add("minlength");
				}
				else
				{
					errors.computeIfAbsent(field, key -> new HashSet<>())
					.add("maxlength");
				}
			}
		}
		return errors;
	}

	private boolean checkExistingEmail(String email)
	{
		User user = userRepo.getUserByEmail(email);

		if (user != null) { return true; }

		return false;
	}

}