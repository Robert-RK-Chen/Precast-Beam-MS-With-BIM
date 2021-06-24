package hibernate.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Robert Chen
 */
@Entity
@Table(name = "curing_info", schema = "precast_beam_system")
public class CuringInfoEntity {
    private String beamId;
    private String curingInspector;
    private Timestamp curingStart;
    private Timestamp curingFinish;

    @Id
    @Column(name = "beam_id")
    public String getBeamId() {
        return beamId;
    }

    public void setBeamId(String beamId) {
        this.beamId = beamId;
    }

    @Basic
    @Column(name = "curing_inspector")
    public String getCuringInspector() {
        return curingInspector;
    }

    public void setCuringInspector(String curingInspector) {
        this.curingInspector = curingInspector;
    }

    @Basic
    @Column(name = "curing_start")
    public Timestamp getCuringStart() {
        return curingStart;
    }

    public void setCuringStart(Timestamp curingStart) {
        this.curingStart = curingStart;
    }

    @Basic
    @Column(name = "curing_finish")
    public Timestamp getCuringFinish() {
        return curingFinish;
    }

    public void setCuringFinish(Timestamp curingFinish) {
        this.curingFinish = curingFinish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CuringInfoEntity that = (CuringInfoEntity) o;
        return Objects.equals(beamId, that.beamId) && Objects.equals(curingInspector, that.curingInspector) && Objects.equals(curingStart, that.curingStart) && Objects.equals(curingFinish, that.curingFinish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beamId, curingInspector, curingStart, curingFinish);
    }
}
