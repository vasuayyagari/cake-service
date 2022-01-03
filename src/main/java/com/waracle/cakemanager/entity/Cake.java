package com.waracle.cakemanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CAKE")
public class Cake {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long U_id;
	private String title;
	private String description;
	private String image;
}
