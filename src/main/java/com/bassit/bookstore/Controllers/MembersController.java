package com.bassit.bookstore.Controllers;

import com.bassit.bookstore.Models.Customers;
import com.bassit.bookstore.Models.Members;
import com.bassit.bookstore.Repo.MembersRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MissingMatrixVariableException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Log
@RestController
public class MembersController {

    @Autowired
    private MembersRepo membersRepo;

    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    //create member
    @PostMapping("/addMember")
    public Members addMembers(@RequestBody Members memberInfo){
        double price = switch (memberInfo.getMembershipPlan()) {
            case "Middle" -> 20.99;
            case "Basic" -> 15.99;
            default -> 29.99;
        };
        String hashPassword = bcrypt.encode(memberInfo.getPassword());
        Members member = new Members();
        member.setMemberFirstName(memberInfo.getMemberFirstName());
        member.setMemberLastName(memberInfo.getMemberLastName());
        member.setMemberEmail(memberInfo.getMemberEmail());
        member.setMemberPhoneNumber(memberInfo.getMemberPhoneNumber());
        member.setUsername(memberInfo.getUsername());
        member.setPassword(hashPassword);
        member.setMembershipPlan(memberInfo.getMembershipPlan());
        member.setMembershipStatus("Active");
        member.setMembershipPrice(price);
        member.setMembershipExpiration(LocalDate.now());
        member.setMembershipPurchaseDate(LocalDate.now());
        log.info("Member added successfully from API endpoint");
        return membersRepo.save(member);
    }

    //read member admin_find by id
    @GetMapping("/getMember/{id}")
    public List<Members> getMembersById(@PathVariable long id){
        log.info("Member found by ID successfully from API endpoint with ID: " + id);
        return membersRepo.findAllById(id);
    }

    //read member by first name and last name
    @GetMapping("/getMember/firstAndLastName")
    public List<Members> getMemberByFirstAndLastName(@RequestBody Members memberInfo){
        log.info("Member found with first name and last name successfully from API endpoint");
        return membersRepo.findAllByMemberFirstNameAndMemberLastName(memberInfo.getMemberFirstName(), memberInfo.getMemberLastName());
    }

    //read member by userName
    @GetMapping("/getMemberByUsername/{username}")
    public List<Members> getMemberByUsername(@PathVariable String username){
        log.info("Member found with username successfully from API endpoint");
        return membersRepo.findAllByUsername(username);
    }

    //read member by email
    @GetMapping("/getMember/email")
    public List<Members> getMembersByEmail(@RequestBody Members membersInfo){
        log.info("Member found with email successfully from API endpoint");
        return membersRepo.findAllByMemberEmail(membersInfo.getMemberEmail());
    }

    //read member by phone
    @GetMapping("/getMember/phoneNumber")
    public List<Members> getMembersByPhoneNumber(@RequestBody Members membersInfo){
        log.info("Member found with phone number successfully from API endpoint");
        return membersRepo.findAllByMemberPhoneNumber(membersInfo.getMemberPhoneNumber());
    }

    //update member first name
    @PostMapping("/updateMember/firstName/{username}")
    public String updateMemberFirstName(@PathVariable String username, @RequestBody Members membersInfo){
        List<Members> membersList = membersRepo.findAllByUsername(username);
        for(Members member : membersList){
            member.setMemberFirstName(membersInfo.getMemberFirstName());
            membersRepo.save(member);
        }
        log.info("Member first name updated successfully from API endpoint for username: " + username);
        return "First Name Successfully updated for: " + username;
    }

    //update member last name
    @PostMapping("/updateMember/lastName/{username}")
    public String updateMemberLastName(@PathVariable String username, @RequestBody Members membersInfo){
        List<Members> membersList = membersRepo.findAllByUsername(username);
        for(Members member : membersList){
            member.setMemberLastName(membersInfo.getMemberLastName());
            membersRepo.save(member);
        }
        log.info("Member last name updated successfully from API endpoint for username: " + username);
        return "Last Name Successfully updated for: " + username;
    }

    //update member username
    @PostMapping("/updateMember/username/{username}")
    public String updateMemberUsername(@PathVariable String username, @RequestBody Members membersInfo){
        List<Members> membersList = membersRepo.findAllByUsername(username);
        for(Members member : membersList){
            member.setUsername(membersInfo.getUsername());
            membersRepo.save(member);
        }
        log.info("Member username updated successfully from API endpoint for old username: " + username);
        return "Username Successfully updated for: " + username;
    }

    //update member password
    @PostMapping("/updateMember/password/{username}")
    public String updateMemberPassword(@PathVariable String username, @RequestBody Members membersInfo){
        List<Members> membersList = membersRepo.findAllByUsername(username);
        for(Members member : membersList){
            member.setPassword(membersInfo.getPassword());
            membersRepo.save(member);
        }
        log.info("Member password updated successfully from API endpoint for username: " + username);
        return "Password Successfully updated for: " + username;
    }

    //update member email
    @PostMapping("/updateMember/email/{username}")
    public String updateMemberEmail(@PathVariable String username, @RequestBody Members membersInfo){
        List<Members> membersList = membersRepo.findAllByUsername(username);
        for(Members member : membersList){
            member.setMemberEmail(membersInfo.getMemberEmail());
            membersRepo.save(member);
        }
        log.info("Member email updated successfully from API endpoint for username: " + username);
        return "Email Successfully updated for: " + username;
    }

    //update member phone
    @PostMapping("/updateMember/phoneNumber/{username}")
    public String updateMemberPhoneNumber(@PathVariable String username, @RequestBody Members membersInfo){
        List<Members> membersList = membersRepo.findAllByUsername(username);
        for(Members member : membersList){
            member.setMemberPhoneNumber(membersInfo.getMemberPhoneNumber());
            membersRepo.save(member);
        }
        log.info("Member phone number updated successfully from API endpoint for username: " + username);
        return "Phone Number Successfully updated for: " + username;
    }

    //update member membership plan
    @PostMapping("/updateMember/membership/{username}")
    public String updateMemberPlan(@PathVariable String username, @RequestBody Members membersInfo){
        double price = switch (membersInfo.getMembershipPlan()) {
            case "Middle" -> 20.99;
            case "Basic" -> 15.99;
            default -> 29.99;
        };
        List<Members> membersList = membersRepo.findAllByUsername(username);
        for(Members member : membersList){
            member.setMembershipPlan(membersInfo.getMembershipPlan());
            member.setMembershipPrice(price);
            membersRepo.save(member);
        }
        log.info("Member membership updated successfully from API endpoint for username: " + username);
        return "Plan Successfully updated for: " + username;
    }

    //update member membership status
    @PostMapping("/updateMember/memberStatus/{username}")
    public String updateMemberStatus(@PathVariable String username, @RequestBody Members membersInfo){
        List<Members> membersList = membersRepo.findAllByUsername(username);
        for(Members member : membersList){
            member.setMembershipStatus(membersInfo.getMembershipStatus());
            membersRepo.save(member);
        }
        log.info("Member membership status updated successfully from API endpoint for username: " + username);
        return "Membership Status Successfully updated for: " + username;
    }

    //update member membership price
    @PostMapping("/updateMember/memberPrice/{username}")
    public String updateMemberPrice(@PathVariable String username, @RequestBody Members membersInfo){
        List<Members> membersList = membersRepo.findAllByUsername(username);
        for(Members member : membersList){
            member.setMembershipPrice(membersInfo.getMembershipPrice());
            membersRepo.save(member);
        }
        log.info("Member membership price updated successfully from API endpoint for username: " + username);
        return "Membership Price Successfully updated for: " + username;
    }

    //update member membership expiration
    @PostMapping("/updateMember/memberExp/{username}/{newExpirationDate}")
    public String updateMemberExp(@PathVariable String username, @PathVariable LocalDate newExpirationDate){
        List<Members> membersList = membersRepo.findAllByUsername(username);
        for(Members member : membersList){
            member.setMembershipExpiration(newExpirationDate);
            membersRepo.save(member);
        }
        log.info("Member membership expiration updated successfully from API endpoint for username: " + username);
        return "Membership Expiration Successfully updated for: " + username;
    }

    //delete member (admin_delete by id)
    @PostMapping("/deleteMember/{id}")
    public String deleteMembership(@PathVariable long id){
        membersRepo.deleteById(id);
        log.info("Member deleted successfully from API endpoint with ID: " + id);
        return "Member Deleted Successfully with ID: " + id;
    }
}
