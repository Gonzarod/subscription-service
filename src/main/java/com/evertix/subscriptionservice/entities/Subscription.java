package com.evertix.subscriptionservice.entities;

import com.evertix.subscriptionservice.model.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
public class Subscription extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean active;


    @Transient
    private User user;

    @Column(name="user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private Plan plan;

}
