package com.valam.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "imageData", uniqueConstraints = {
		@UniqueConstraint(columnNames ="imageId")
})
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String name;

    private String type;

    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;
    
    @Column(name="id")
    private Long  id;
    
    
}