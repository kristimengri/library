package com.kent.gmail.com.runtime.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.OffsetDateTime;

@Entity
public class BookInstanceCondition extends Base {

    @ManyToOne
    @JoinColumn(name = "book_instance_id", nullable = false)
    private BookInstance bookInstance;

    private String conditionDescription; // e.g., "New", "Good", "Worn", "Damaged"
    private OffsetDateTime conditionDate; // Date when the condition was recorded

    // Getters and Setters
    public BookInstance getBookInstance() {
        return bookInstance;
    }

    public void setBookInstance(BookInstance bookInstance) {
        this.bookInstance = bookInstance;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }

    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    public OffsetDateTime getConditionDate() {
        return conditionDate;
    }

    public void setConditionDate(OffsetDateTime conditionDate) {
        this.conditionDate = conditionDate;
    }
}