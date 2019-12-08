package com.cs201.sendo.recommend.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(UserCountViewId.class)
@Table(name = "user_views_count")
public class UserViewCount implements Serializable {
    @Id
    private Long userId;
    @Id
    @Column(name = "cat_lv2_id")
    private Long cateLv2Id;
    private Integer count;
    private String sId;

    @Override
    public String toString() {
        return userId +
                "," + cateLv2Id +
                "," + count +
                "," + sId;
    }
}
