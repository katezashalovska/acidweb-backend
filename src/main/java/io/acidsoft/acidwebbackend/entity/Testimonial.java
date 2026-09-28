package io.acidsoft.acidwebbackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "testimonials")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_name", nullable = false, length = 100)
    private String authorName;

    @Column(nullable = false, length = 100)
    private String role;

    @Column(nullable = false, length = 100)
    private String company;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String quote;

    @Column(length = 100)
    private String metric;

    @Column(name = "order_index")
    private Integer orderIndex;
}
