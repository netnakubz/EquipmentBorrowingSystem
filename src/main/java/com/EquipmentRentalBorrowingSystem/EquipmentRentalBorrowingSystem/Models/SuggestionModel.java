package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "suggestion")
public class SuggestionModel {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "item_ID")
    @JsonIgnore
    private EquipmentModel equipmentModel;

    public SuggestionModel() {
    }

    public SuggestionModel(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EquipmentModel getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(EquipmentModel equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    @Override
    public String toString() {
        return "SuggestionModel{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", equipmentModel=" + equipmentModel +
                '}';
    }
}
