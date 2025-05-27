package com.zwash.pojos;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales")
public class Sales {

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getDailySales() {
		return dailySales;
	}

	public void setDailySales(double dailySales) {
		this.dailySales = dailySales;
	}

	public double getWeeklySales() {
		return weeklySales;
	}

	public void setWeeklySales(double weeklySales) {
		this.weeklySales = weeklySales;
	}

	public double getMonthlySales() {
		return monthlySales;
	}

	public void setMonthlySales(double monthlySales) {
		this.monthlySales = monthlySales;
	}

	public double getYearlySales() {
		return yearlySales;
	}

	public void setYearlySales(double yearlySales) {
		this.yearlySales = yearlySales;
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double dailySales;

    @Column(nullable = false)
    private double weeklySales;

    @Column(nullable = false)
    private double monthlySales;

    @Column(nullable = false)
    private double yearlySales;


}
