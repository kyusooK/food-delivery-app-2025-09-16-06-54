package fooddeliveryapp.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

//<<< DDD / Value Object
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long menuId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Double price;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private String description;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private String image;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date createdAt;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date updatedAt;
}
//>>> DDD / Value Object
