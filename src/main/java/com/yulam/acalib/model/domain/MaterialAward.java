package com.yulam.acalib.model.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "material_award", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"code"})
})
public class MaterialAward {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "title", nullable = false, length = 100)
  private String title;

  @Column(name = "code", nullable = false, length = 20)
  private String code;

  @Column(name = "description", length = 500)
  private String description;
}
