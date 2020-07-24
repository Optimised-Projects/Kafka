package com.optimised_buildings_ltd.kafka;

import com.tridium.ndriver.BNNetwork;

import javax.baja.driver.BDeviceNetwork;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

@NiagaraType

public class BKafkaNetwork extends BNNetwork {

    public Type getDeviceType(){
        return BKafkaDevice.TYPE;
    }

    public Type getDeviceFolderType(){
        return BKafkaDeviceFolder.TYPE;
    }

    public String getNetworkName(){
        return this.getName();
    }

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimised_buildings_ltd.kafka.BKafkaNetwork(2979906276)1.0$ @*/
/* Generated Thu Jul 23 16:10:37 BST 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BKafkaNetwork.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


}
