package com.darwinbox.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name = "t_office_location", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"companyName", "area", "address", "workArea", "created", "updated"})
})
@Data
public class OfficeLocationEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6842387023020939700L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String companyName;

	private String locTypeId;

	private String locationType;

	private String cityType;

	private String centreType;

	private String costCenterCode;

	private String costCenter;

	private String area;

	private String city;

	private String state;

	private String country;

	private String countryCode;

	private String countryId;

	private String workArea;

	private String locationArea;

	private String pinCode;

	private String address;

	private String status;

	private Date created;

	private Date updated;
}
