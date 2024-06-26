package dev.Mahathir.JwtSecurity.service;


import dev.Mahathir.JwtSecurity.repo.UserInfoRepo;
import dev.Mahathir.JwtSecurity.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class ResponseService {

    private final UserInfoRepo userRepo;

//    private final RoleRepository roleRepo;

    @Autowired
    public ResponseService(UserInfoRepo userRepo){//, RoleRepository roleRepo) {
        this.userRepo = userRepo;
//        this.roleRepo = roleRepo;
    }


    //get user response



    //create user
    public ResponseEntity<String> createNewUser(User userData) {

        try{
            LocalDate localDate = LocalDate.now();
            Date date = Date.valueOf(localDate);
            userData.setCreatedOn(date);

//            List<Role> validRoles = new ArrayList<>();
//
//
//            for(Role role : userData.getRoles()){
//                Optional<Role> optionalRole = roleRepo.findByName(role.getName());
//                if(optionalRole.isPresent()){
//                    validRoles.add(optionalRole.get());
//                }else {
//                    throw new Exception(role.getName() + " Role invalid");
//                }
//            }
//            userData.setRoles(validRoles);
            userRepo.save(userData);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

//    public List<UserInfo> getAllUser(){
//        return userRepo.findAllById();
//    }

    public void editUser(Integer id, User user){
        User existingUser = userRepo.findById(id).orElseThrow(() ->new RuntimeException("User not found"));

        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPhoneNo(user.getPhoneNo());
        existingUser.setUserStatus(user.getUserStatus());
        //existingUser.setPassword(user.getPassword());

        userRepo.save(existingUser);
    }



    public String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
