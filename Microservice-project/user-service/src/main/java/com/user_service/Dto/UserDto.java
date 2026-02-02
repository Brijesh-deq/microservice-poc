package com.user_service.Dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userName;
    private String dob;
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    @Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits")
    private String mobileNo;

}
