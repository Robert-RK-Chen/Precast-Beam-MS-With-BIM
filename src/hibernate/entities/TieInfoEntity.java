package hibernate.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Robert Chen
 */
@Entity
@Table(name = "tie_info", schema = "precast_beam_system")
public class TieInfoEntity
{
    private String beamId;
    private String wireInspector;
    private Timestamp wireStart;
    private Timestamp wireFinish;

    @Id
    @Column(name = "beam_id")
    public String getBeamId()
    {
        return beamId;
    }

    public void setBeamId(String beamId)
    {
        this.beamId = beamId;
    }

    @Basic
    @Column(name = "wire_inspector")
    public String getWireInspector()
    {
        return wireInspector;
    }

    public void setWireInspector(String wireInspector)
    {
        this.wireInspector = wireInspector;
    }

    @Basic
    @Column(name = "wire_start")
    public Timestamp getWireStart()
    {
        return wireStart;
    }

    public void setWireStart(Timestamp wireStart)
    {
        this.wireStart = wireStart;
    }

    @Basic
    @Column(name = "wire_finish")
    public Timestamp getWireFinish()
    {
        return wireFinish;
    }

    public void setWireFinish(Timestamp wireFinish)
    {
        this.wireFinish = wireFinish;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        TieInfoEntity that = (TieInfoEntity) o;
        return Objects.equals(beamId, that.beamId) && Objects.equals(wireInspector, that.wireInspector) && Objects.equals(wireStart, that.wireStart) && Objects.equals(wireFinish, that.wireFinish);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(beamId, wireInspector, wireStart, wireFinish);
    }
}
