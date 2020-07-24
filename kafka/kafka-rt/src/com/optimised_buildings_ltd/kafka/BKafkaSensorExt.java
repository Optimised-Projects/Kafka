package com.optimised_buildings_ltd.kafka;

import javax.baja.control.BNumericPoint;
import javax.baja.control.ext.BAbstractProxyExt;
import javax.baja.driver.point.BProxyExt;
import javax.baja.driver.point.BReadWriteMode;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.status.BStatus;
import javax.baja.status.BStatusNumeric;
import javax.baja.sys.*;
import java.util.function.Consumer;


@NiagaraType
@NiagaraProperty(name = "pointId", type = "BString", defaultValue = "BString.DEFAULT")
@NiagaraAction(name = "readMessage", parameterType = "BString", defaultValue = "BString.DEFAULT")

public class BKafkaSensorExt extends BProxyExt {

  public boolean write(Context c) throws Exception {
    return false;
  }

  public void readSubscribed(Context cx){

  }

  public void readUnsubscribed(Context cx){

  }

  public Type getDeviceExtType(){
    return BKafkaPointDeviceExt.TYPE;
  }

  public BReadWriteMode getMode(){
    return BReadWriteMode.make(0);
  }

  public void doReadMessage(BString message){

    Double value = Double.parseDouble(message.getString());
    BStatusNumeric val = new BStatusNumeric(value);
    this.readOk(val);


  }

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimised_buildings_ltd.kafka.BKafkaSensorExt(1324112337)1.0$ @*/
/* Generated Fri Jul 24 10:56:47 BST 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "pointId"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code pointId} property.
   * @see #getPointId
   * @see #setPointId
   */
  public static final Property pointId = newProperty(0, BString.DEFAULT, null);
  
  /**
   * Get the {@code pointId} property.
   * @see #pointId
   */
  public String getPointId() { return getString(pointId); }
  
  /**
   * Set the {@code pointId} property.
   * @see #pointId
   */
  public void setPointId(String v) { setString(pointId, v, null); }

////////////////////////////////////////////////////////////////
// Action "readMessage"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code readMessage} action.
   * @see #readMessage(BString parameter)
   */
  public static final Action readMessage = newAction(0, BString.DEFAULT, null);
  
  /**
   * Invoke the {@code readMessage} action.
   * @see #readMessage
   */
  public void readMessage(BString parameter) { invoke(readMessage, parameter, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BKafkaSensorExt.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
}
