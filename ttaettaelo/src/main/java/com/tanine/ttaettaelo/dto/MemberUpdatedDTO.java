package com.tanine.ttaettaelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberUpdatedDTO {

    private Long memberId;
    private String loginId;
    private String password;
    private String passwordCheck;
    private String name;
    private String email;
    private String gender;
    private String address;
}
