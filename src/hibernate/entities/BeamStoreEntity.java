package hibernate.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Robert Chen
 */
@Entity
@Table(name = "beam_store", schema = "precast_beam_system")
public class BeamStoreEntity {
    private String beamId;
    private String storeInspector;
    private Timestamp storeStart;
    private Timestamp shipmentExpect;
    private Timestamp shipmentActual;

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
    @Column(name = "store_inspector")
    public String getStoreInspector()
    {
        return storeInspector;
    }

    public void setStoreInspector(String storeInspector)
    {
        this.storeInspector = storeInspector;
    }

    @Basic
    @Column(name = "store_start")
    public Timestamp getStoreStart()
    {
        return storeStart;
    }

    public void setStoreStart(Timestamp storeStart)
    {
        this.storeStart = storeStart;
    }

    @Basic
    @Column(name = "shipment_expect")
    public Timestamp getShipmentExpect()
    {
        return shipmentExpect;
    }

    public void setShipmentExpect(Timestamp shipmentExpect)
    {
        this.shipmentExpect = shipmentExpect;
    }

    @Basic
    @Column(name = "shipment_actual")
    public Timestamp getShipmentActual()
    {
        return shipmentActual;
    }

    public void setShipmentActual(Timestamp shipmentActual)
    {
        this.shipmentActual = shipmentActual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        BeamStoreEntity that = (BeamStoreEntity) o;
        return Objects.equals(beamId, that.beamId) && Objects.equals(storeInspector, that.storeInspector) && Objects.equals(storeStart, that.storeStart) && Objects.equals(shipmentExpect, that.shipmentExpect) && Objects.equals(shipmentActual, that.shipmentActual);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(beamId, storeInspector, storeStart, shipmentExpect, shipmentActual);
    }
}
