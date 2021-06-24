package hibernate.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Robert Chen
 */
@Entity
@Table(name = "pouring_info", schema = "precast_beam_system")
public class PouringInfoEntity {
    private String beamId;
    private String pouringInspector;
    private Timestamp pouringStart;
    private Timestamp pouringFinish;

    @Id
    @Column(name = "beam_id")
    public String getBeamId() {
        return beamId;
    }

    public void setBeamId(String beamId) {
        this.beamId = beamId;
    }

    @Basic
    @Column(name = "pouring_inspector")
    public String getPouringInspector() {
        return pouringInspector;
    }

    public void setPouringInspector(String pouringInspector) {
        this.pouringInspector = pouringInspector;
    }

    @Basic
    @Column(name = "pouring_start")
    public Timestamp getPouringStart() {
        return pouringStart;
    }

    public void setPouringStart(Timestamp pouringStart) {
        this.pouringStart = pouringStart;
    }

    @Basic
    @Column(name = "pouring_finish")
    public Timestamp getPouringFinish() {
        return pouringFinish;
    }

    public void setPouringFinish(Timestamp pouringFinish) {
        this.pouringFinish = pouringFinish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PouringInfoEntity that = (PouringInfoEntity) o;
        return Objects.equals(beamId, that.beamId) && Objects.equals(pouringInspector, that.pouringInspector) && Objects.equals(pouringStart, that.pouringStart) && Objects.equals(pouringFinish, that.pouringFinish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beamId, pouringInspector, pouringStart, pouringFinish);
    }
}
