package com.optimised_buildings_ltd.kafka;

import javax.baja.driver.point.BPointDeviceExt;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

@NiagaraType

public class BKafkaPointDeviceExt extends BPointDeviceExt {

    public Type getDeviceType(){
        return BKafkaDevice.TYPE;
    }

    public Type getProxyExtType(){
        return BKafkaSensorExt.TYPE;
    }

    public Type getPointFolderType(){
        return BKafkaPointFolder.TYPE;
    }

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimised_buildings_ltd.kafka.BKafkaPointDeviceExt(2979906276)1.0$ @*/
/* Generated Fri Jul 24 09:59:47 BST 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BKafkaPointDeviceExt.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
}
