package com.ec.servicecenter.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "edge_info")
public class EdgeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long edgeId;

    @Column(columnDefinition = "nvarchar(256)", nullable = false)
    private String location;

    @Column(columnDefinition = "nvarchar(256)", nullable = false)
    private String ipAddress;

    @Column(columnDefinition = "Decimal(10,2)", nullable = false)
    private BigDecimal charge;


    @Column(columnDefinition = "tinyint(1)")
    @Builder.Default
    private boolean active = true;
}
