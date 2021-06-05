package hibernate.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Robert Chen
 */
@Entity
@Table(name = "steel_info", schema = "precast_beam_system")
public class SteelInfoEntity {
    private String steelType;
    private Double diameter;

    @Id
    @Column(name = "steel_type")
    public String getSteelType()
    {
        return steelType;
    }

    public void setSteelType(String steelType)
    {
        this.steelType = steelType;
    }

    @Basic
    @Column(name = "diameter")
    public Double getDiameter()
    {
        return diameter;
    }

    public void setDiameter(Double diameter)
    {
        this.diameter = diameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        SteelInfoEntity that = (SteelInfoEntity) o;
        return Objects.equals(steelType, that.steelType) && Objects.equals(diameter, that.diameter);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(steelType, diameter);
    }
}
