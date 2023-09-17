package com.valam.app.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.dto.ImageDto;
import com.valam.app.dto.ResponseMessage;
import com.valam.app.dto.UserDto;
import com.valam.app.exception.ResourceNotFoundException;
import com.valam.app.model.CommonAPIToken;
import com.valam.app.model.LoginUsers;
import com.valam.app.model.RideStatus;
import com.valam.app.model.User;
import com.valam.app.payload.SignUpRequest;
import com.valam.app.repo.UserRepository;
import com.valam.app.security.CurrentUser;
import com.valam.app.security.TokenProvider;
import com.valam.app.security.UserPrincipal;
import com.valam.app.service.CommonApiTokenService;
import com.valam.app.service.ImageUpload;
import com.valam.app.service.JWTTokenCreator;
import com.valam.app.service.LoginUsersService;
import com.valam.app.service.StatusService;
import com.valam.app.service.UserService;

import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    
    @Autowired
    private CommonApiTokenService tokenService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	private ImageUpload imageUpload;

    String testStr = null;
    
    @Autowired
    private JWTTokenCreator jwtCreator;

    @Autowired
    private TokenProvider tokenProvider;
    
    @Autowired
    private LoginUsersService loginService;
    
    @Autowired
    private StatusService statusService;
    

    //login request with email and password with valid credentials.and it stores through authentication token
    //@PostMapping("/login")
    /*
     * public ResponseEntity<?> authenticationUser(@Valid @RequestBody LoginRequest
     * loginRequest) {
     *
     * Authentication authentication = authenticationManager.authenticate(new
     * UsernamePasswordAuthenticationToken(
     *
     * loginRequest.getEmail(), loginRequest.getPassword() ) );
     * SecurityContextHolder.getContext().setAuthentication(authentication); String
     * token = tokenProvider.createToken(authentication); return
     * ResponseEntity.ok(new AuthResponse(token)); }
     */

    @ApiOperation(value = "api to user signup with valid details.")
    @PostMapping("/signup")
    public UserDto registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    	  UserDto userDto = new UserDto();
    	  CommonAPIToken apiToken = new CommonAPIToken();
    	  boolean isExist  = userRepository.existsByEmail(signUpRequest.getEmail());
        if(userRepository.findByMobileNumAndEmail(signUpRequest.getPhNum(),signUpRequest.getEmail()) != null) {
        	  userDto.setHttpStatus(200);
              userDto.setMessage("Email & Mobile exist");
        }else if(userRepository.findByMobileNumAndEmail(null,signUpRequest.getEmail()) != null) {
        	 userDto.setHttpStatus(400);
             userDto.setMessage("Email exist");
        }else if(userRepository.findByMobileNumAndEmail(signUpRequest.getPhNum(),null) != null) {
       	 userDto.setHttpStatus(400);
         userDto.setMessage("Mobile exist");
        }else if(isExist == false) {
        	 User result = userService.saveUser(signUpRequest);
             userDto.setId(result.getId());
             userDto.setName( result.getName());
             userDto.setEmail(result.getEmail());
             userDto.setImageUrl(result.getImageUrl());
             userDto.setPhNum(result.getPhNum());
             userDto.setToken(result.getToken());
             apiToken.setAuth_common_id("User"+"-"+LocalDate.now()+"-"+String.valueOf(((int) (Math.random() * (10000 - 1000))) + 1000));
 			 apiToken.setLoggedin_user_name("User"+userDto.getFirstName());
 			 CommonAPIToken apiToken1 = tokenService.save(apiToken);
 			 userDto.setCommon_token_id(apiToken1.getAuth_common_id());             
        }
       // URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/me")
        //        .buildAndExpand(result.getId()).toUri();
        return userDto;
    }

	/*
	 * @ApiOperation(value = "api to add new record of user")
	 * 
	 * @PostMapping("/add") public User addUser(@RequestBody User user) { return
	 * userService.saveUser(user); }
	 */

    @ApiOperation(value = "api to fetch the all users record")
    @GetMapping("/all")
    public List<User> findAll() {
        return userService.getUsers();
    }

    @ApiOperation(value = "api to update user record by id")
    @PutMapping("/update")
    public UserDto Update(@RequestHeader(value="token") String token,@RequestBody User user) {
    	UserDto userData = null;
    	if(userService.findByToken(token).getEmail() != null) {
    		userData = userService.userUpdate(user);
    		return userData;
    	}else {
    		return userData;
    	}
    }

    @ApiOperation(value = "api to get user by using token")
    @GetMapping("/user-info")
    public User getUserInfo(HttpServletRequest request) {

        String jwt = getJwtFromRequest(request);
        Long userId = tokenProvider.getUserIdFromToken(jwt);
        return userService.getUserById(userId);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    @ApiOperation(value = "api to get current user")
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @ApiOperation(value = "api to verify existing user by email")
    @PostMapping("/get-email")
    public boolean getByEmail(@RequestHeader(value="token") String token, @RequestBody UserDto user) {
    	boolean value = false;
    	if(userService.findByToken(token).getEmail() !=null) {
    		value = userService.CheckUsernameExists(user.getEmail());
    	   System.out.println(value);
    	}
        return value;
    }

//    @ApiOperation(value = "api to verify existing user by phone number or e-mail")
//    @PostMapping("/login")
//    public User userAuthentication(@RequestBody UserDto user) {
//
//    	 if (userService.findByPhNumAndEmail(user) != null) {
//        	 return userService.findByPhNumAndEmail(user);
//        } else
//            return null;
//    }
    
    @ApiOperation(value = "api to get current user")
    @GetMapping("/user/{id}")
    public User getUserByID(@RequestHeader(value="authorization") String token,@PathVariable Long id) {
    	User user = userRepository.findByToken(token);
    	System.out.println(user.getEmail());
    	if(userRepository.findByToken(token) != null) { 
    		return userService.getUserById(id);
    	}else 
    		return null;
    }
    
//    @ApiOperation(value = "api to upload image of current user")
//    @PostMapping("/addImage/{id}")
//    public String uploadImage(@PathVariable long id,@RequestParam("file") MultipartFile file) {
//    	return imageUpload.addImage(id, file);
//    }
    
    @ApiOperation(value = "api to upload image of current user")
    @PostMapping("/addImage")
    public ResponseMessage uploadImage(@RequestHeader(value="token") String token,@RequestBody ImageDto imageDto) {
    	//long id = imageDto.getId();
    	//MultipartFile file = imageDto.getFile();
    	//imageUpload.addImage(id, file);
    	  ResponseMessage message = new ResponseMessage();
    	if(userRepository.findByToken(token).getEmail() != null) {
    		userRepository.updateImage(imageDto.getImageUrl(), imageDto.getId());
            message.setHttpStatus(200);
            message.setMessage("Successfully Inserted Image");
            return message;
    	}else {
    		 message.setHttpStatus(400);
             message.setMessage("Not Inserted ");
             return message;
    	}
    }
    
//    @PostMapping("/addImage")
//    public ResponseMessage compressByte(@RequestBody ImageDto imageDto) throws IOException {
//    	testStr = imageDto.getImageUrl();
//        byte[] input = imageDto.getImageUrl().getBytes();        
//        byte[] op = CompressionUtil.compress(input);       
//        System.out.println("original data length " + input.length + ",  compressed data length " + op.length);
//        byte[] org = CompressionUtil.decompress(op);       
//        System.out.println(org.length);       
//        System.out.println(new String(org, StandardCharsets.UTF_8));    
//        String op1 = CompressionUtil.compressAndReturnB64(imageDto.getImageUrl());       
//        System.out.println("Compressed data b64" + op1);
//        String org1 = CompressionUtil.decompressB64(op1);        
//        System.out.println("Original text" + org1 + op1);  
//        byte[] input12 = op1.getBytes();        
//        byte[] op12 = CompressionUtil.compress(input12);     
//        ResponseMessage message = new ResponseMessage();
//        message.setMessage(op1+"_"+org1+"ajay"+op12);
//        System.out.println("original data length " + input12.length + ",  compressed data length " + op12.length);
//        return message;
//    }   
    
    @ApiOperation(value = "api to verify existing user by phone number or e-mail")
    @PostMapping("/login")
	public UserDto login(@RequestBody UserDto userDto) {
    	UserDto user = new UserDto();
    	CommonAPIToken apiToken = new CommonAPIToken();
		
		User u = userService.findByPhNumAndEmail(userDto);
		String token = jwtCreator.getJWTToken(u.getEmail());
		if(u != null) {
			user.setId(u.getId());
			user.setToken(token);
			userService.updateUserToken(user);
			apiToken.setAuth_common_id("User"+"-"+LocalDate.now()+"-"+String.valueOf(((int) (Math.random() * (10000 - 1000))) + 1000));
			apiToken.setLoggedin_user_name("User"+u.getFirstName());
			CommonAPIToken apiToken1 = tokenService.save(apiToken);
			//User userData = userService.findByPhNumAndEmail(userDto);
			user.setCreatedDate(u.getCreatedDate());
			user.setEmail(u.getEmail());
			user.setExist(true);
			user.setFirstName(u.getFirstName());
			user.setId(u.getId());
			user.setImageUrl(u.getImageUrl());
			user.setLastName(u.getLastName());
			user.setMiddleName(u.getMiddleName());
			user.setName(u.getMiddleName());
			user.setPhNum(u.getPhNum());
			user.setSocial_signup_id(u.getSocial_signup_id());
			user.setToken(token);
			user.setCommon_token_id(apiToken1.getAuth_common_id());
			return user;
		}else {
            return null;
		}
	}
}
