package com.yulam.acalib.model.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email_address"})
})
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "first_name", nullable = false, length = 100)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 100)
  private String lastName;

  @Column(name = "email_address", nullable = false, length = 150)
  private String emailAddress;

  @Column(name = "password_hash", nullable = false, length = 500)
  private String password;

  @Column(name = "avatar", length = 500)
  private String avatar;

  private Boolean emailAddressVerified;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "member_role",
    joinColumns = {
          @JoinColumn(name = "member_id")
    },
    inverseJoinColumns = {
          @JoinColumn(name = "role_id")
    })
  private Set<Role> roles;

  @CreationTimestamp
  @Column(name = "created_on")
  private LocalDateTime createdOn;

  @UpdateTimestamp
  @Column(name = "updated_on")
  private LocalDateTime updatedOn;

}
