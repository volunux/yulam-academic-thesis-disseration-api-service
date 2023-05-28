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
@Table(name ="material")
public class Material {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "title", nullable = false, length = 500)
  private String title;

  @Column(name = "material_abstract", nullable = false, length = 5000)
  private String materialAbstract;

  @ManyToOne
  @JoinColumn(name = "grade_id")
  private Grade grade;

  @ManyToOne
  @JoinColumn(name = "material_award_id")
  private MaterialAward materialAward;

  @Column(name = "year")
  private Integer year;

  @ManyToOne
  @JoinColumn(name = "author_id", nullable = false)
  private Author author;

  @Column(name = "material_type", nullable = false, length = 200)
  @Enumerated(value = EnumType.STRING)
  private MaterialType materialType;

  @CreationTimestamp
  @Column(name = "created_on")
  private LocalDateTime createdOn;

  @UpdateTimestamp
  @Column(name = "updated_on")
  private LocalDateTime updatedOn;

}
