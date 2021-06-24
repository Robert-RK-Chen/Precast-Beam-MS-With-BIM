package hibernate.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Robert Chen
 */
@Entity
@Table(name = "beam_info", schema = "precast_beam_system")
public class BeamInfoEntity {
    private String beamId;
    private String beamKind;
    private String steelType1;
    private String steelType2;
    private String steelType3;
    private Double length;
    private Double width;
    private Double radius;
    private Double height;
    private String beamState;

    @Id
    @Column(name = "beam_id")
    public String getBeamId() {
        return beamId;
    }

    public void setBeamId(String beamId) {
        this.beamId = beamId;
    }

    @Basic
    @Column(name = "beam_kind")
    public String getBeamKind() {
        return beamKind;
    }

    public void setBeamKind(String beamKind) {
        this.beamKind = beamKind;
    }

    @Basic
    @Column(name = "steel_type_1")
    public String getSteelType1() {
        return steelType1;
    }

    public void setSteelType1(String steelType1) {
        this.steelType1 = steelType1;
    }

    @Basic
    @Column(name = "steel_type_2")
    public String getSteelType2() {
        return steelType2;
    }

    public void setSteelType2(String steelType2) {
        this.steelType2 = steelType2;
    }

    @Basic
    @Column(name = "steel_type_3")
    public String getSteelType3() {
        return steelType3;
    }

    public void setSteelType3(String steelType3) {
        this.steelType3 = steelType3;
    }

    @Basic
    @Column(name = "length")
    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    @Basic
    @Column(name = "width")
    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    @Basic
    @Column(name = "radius")
    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Basic
    @Column(name = "height")
    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Basic
    @Column(name = "beam_state")
    public String getBeamState() {
        return beamState;
    }

    public void setBeamState(String beamState) {
        this.beamState = beamState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BeamInfoEntity that = (BeamInfoEntity) o;
        return Objects.equals(beamId, that.beamId) && Objects.equals(beamKind, that.beamKind) && Objects.equals(steelType1, that.steelType1) && Objects.equals(steelType2, that.steelType2) && Objects.equals(steelType3, that.steelType3) && Objects.equals(length, that.length) && Objects.equals(width, that.width) && Objects.equals(radius, that.radius) && Objects.equals(height, that.height) && Objects.equals(beamState, that.beamState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beamId, beamKind, steelType1, steelType2, steelType3, length, width, radius, height, beamState);
    }
}
