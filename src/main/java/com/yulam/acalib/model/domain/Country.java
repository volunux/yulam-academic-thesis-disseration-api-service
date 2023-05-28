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
@Table(name = "country", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"code"})
})
public class Country {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "title", nullable = false, length = 100)
  private String title;

  @Column(name = "code", nullable = false, length = 5)
  private String code;

  @Column(name = "founding_year")
  private Integer foundingYear;

  @Column(name = "map_logo_url", length = 500)
  private String mapLogoUrl;

  @CreationTimestamp
  @Column(name = "created_on")
  private LocalDateTime createdOn;

  @UpdateTimestamp
  @Column(name = "updated_on")
  private LocalDateTime updatedOn;
}
