package com.optimised_buildings_ltd.kafka;

import javax.baja.control.BNumericPoint;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

@NiagaraType
@NiagaraProperty(name = "proxyExt", type = "BKafkaSensorExt", defaultValue = "new BKafkaSensorExt()")

public class BKafkaNumericSensor extends BNumericPoint {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimised_buildings_ltd.kafka.BKafkaNumericSensor(1812487309)1.0$ @*/
/* Generated Fri Jul 24 10:22:18 BST 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "proxyExt"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code proxyExt} property.
   * @see #getProxyExt
   * @see #setProxyExt
   */
  public static final Property proxyExt = newProperty(0, new BKafkaSensorExt(), null);
  
  /**
   * Get the {@code proxyExt} property.
   * @see #proxyExt
   */
  public BKafkaSensorExt getProxyExt() { return (BKafkaSensorExt)get(proxyExt); }
  
  /**
   * Set the {@code proxyExt} property.
   * @see #proxyExt
   */
  public void setProxyExt(BKafkaSensorExt v) { set(proxyExt, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BKafkaNumericSensor.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
}
