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
@Table(name = "department", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"code"})
})
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "title", nullable = false, length = 200)
  private String title;

  @Column(name = "code", nullable = false, length = 5)
  private String code;

  @Column(name = "description", length = 1000)
  private String description;

  @ManyToOne
  @JoinColumn(name ="faculty_id", nullable = false)
  private Faculty faculty;

  @CreationTimestamp
  @Column(name = "created_on")
  private LocalDateTime createdOn;

  @UpdateTimestamp
  @Column(name = "updated_on")
  private LocalDateTime updatedOn;
}
