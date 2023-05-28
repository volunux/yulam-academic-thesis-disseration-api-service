package com.yulam.acalib.model.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "institution_email_address", length = 150)
  private String institutionEmailAddress;

  @Column(name ="institution_email_address_verified")
  private Boolean institutionEmailAddressVerified;

  @Column(name = "graduation_year")
  private Integer graduationYear;

  @OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "member_id", referencedColumnName = "id")
  private Member member;

  @CreationTimestamp
  @Column(name = "created_on")
  private LocalDateTime createdOn;

  @UpdateTimestamp
  @Column(name = "updated_on")
  private LocalDateTime updatedOn;

}
