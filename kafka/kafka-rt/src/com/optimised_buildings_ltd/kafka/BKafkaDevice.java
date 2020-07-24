package com.optimised_buildings_ltd.kafka;

import com.tridium.ndriver.BNNetwork;
import com.tridium.ndriver.datatypes.BIpAddress;
import com.tridiumx.mqttClientDriver.BAbstractMqttDriverDevice;
import com.tridiumx.mqttClientDriver.BAbstractMqttDriverNetwork;
import com.tridiumx.mqttClientDriver.point.BMqttClientDriverPointDeviceExt;
import com.tridiumx.mqttClientDriver.proxyExt.subscribers.BMqttStringObjectSubscribeExt;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import javax.baja.collection.BITable;
import javax.baja.driver.BDevice;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import javax.baja.util.IFuture;
import javax.baja.util.Invocation;
import javax.management.MBeanPermission;
import java.net.InetAddress;
import java.security.acl.Permission;
import java.util.Collections;
import java.util.Properties;

@NiagaraType
@NiagaraProperty(name = "message", type = "BString", defaultValue = "BString.DEFAULT") //TODO HIDE
@NiagaraProperty(name = "topic", type = "BString", defaultValue = "BString.DEFAULT")
@NiagaraProperty(name = "autocreate", type = "BBoolean", defaultValue = "BBoolean.FALSE")
@NiagaraProperty(name = "points", type = "BKafkaPointDeviceExt", defaultValue = "new BKafkaPointDeviceExt()")



public class BKafkaDevice extends BDevice {

    public void started(){
        if(Sys.getStation().isRunning()) {
            this.add("mqttInterface", new BAbstractMqttDriverNetwork());
            BAbstractMqttDriverNetwork mqttInterface = (BAbstractMqttDriverNetwork)this.get("mqttInterface");
            mqttInterface.add("mqttServer",new BAbstractMqttDriverDevice());
            BAbstractMqttDriverDevice mqttServer = (BAbstractMqttDriverDevice) mqttInterface.get("mqttServer");
            BMqttClientDriverPointDeviceExt pointsFolder = mqttServer.getPoints();
            pointsFolder.add("receiver", new BMqttStringSubscriberPoint());
            BMqttStringSubscriberPoint receiver = (BMqttStringSubscriberPoint) pointsFolder.get("receiver");
            BMqttStringObjectSubscribeExt ext = receiver.getProxyExt();
            ext.setTopic(this.getTopic());
            this.add("messageLink", this.makeLink(receiver,receiver.getSlot("out"),this.getSlot("message"),null));
            ext.subscribe();
        }
    }

    public void changed(Property prop, Context cx){
        if(prop.equals(message)){
            //TODO GET DATA FROM MESSAGE
            String pointId = "testId";
            //LOADING THE MESSAGE HERE TO PREVENT MESSAGE CHANGING WHILE BQL RESOLVES
            String message = this.getMessage();
            String bql = "station:|" + this.asComponent().getSlotPath() + "|bql:select * from kafka:KafkaSensorExt where pointId = '" + pointId + "'";
            //THIS METHOD MAY NEED CHANGING IF NOT FAST ENOUGH.
            BITable result = (BITable) BOrd.make(bql).resolve().get();
            Cursor c = result.cursor();
            boolean foundAtLeastOne = false;
            while(c.next()){
                foundAtLeastOne = true;
                BKafkaSensorExt ext = (BKafkaSensorExt)c.get();
                this.writeToExtension(ext, message);
            }
            if(!foundAtLeastOne && this.getAutocreate()){
                //TODO HANDLE METHOD BASED ON DIFFERENT DATA TYPES
                this.getPoints().add(pointId,new BKafkaNumericSensor());
                BKafkaSensorExt ext = (BKafkaSensorExt)((BKafkaNumericSensor)this.getPoints().get(pointId)).getProxyExt();
                ext.setPointId(pointId);
                this.writeToExtension(ext,message);
            }

        } else if(prop.equals(topic)){
            BAbstractMqttDriverNetwork mqttInterface = (BAbstractMqttDriverNetwork)this.get("mqttInterface");
            BAbstractMqttDriverDevice mqttServer = (BAbstractMqttDriverDevice) mqttInterface.get("mqttServer");
            BMqttClientDriverPointDeviceExt pointsFolder = mqttServer.getPoints();
            BMqttStringSubscriberPoint receiver = (BMqttStringSubscriberPoint) pointsFolder.get("receiver");
            BMqttStringObjectSubscribeExt ext = receiver.getProxyExt();
            ext.unsubscribe();
            ext.setTopic(this.getTopic());
            ext.subscribe();
        }

    }

    private void writeToExtension(BKafkaSensorExt ext, String message){
        //TODO DO STUFF WITH EXT

        //TEST CODE
        ext.doReadMessage(BString.make(message));
    }

    public Type getNetworkType(){
        return BKafkaNetwork.TYPE;
    }

    public void doPing(){
       try {
           BAbstractMqttDriverNetwork mqttInterface = (BAbstractMqttDriverNetwork)this.get("mqttInterface");
           BAbstractMqttDriverDevice mqttServer = (BAbstractMqttDriverDevice) mqttInterface.get("mqttServer");
            InetAddress address = InetAddress.getByName(mqttServer.getBrokerIpAddress());
            if(address.isReachable(3000)){
                this.configOk();
            } else {
                this.configFail("Host unreachable");
            }
        } catch (Exception e){
            this.configFail(e.toString());
        }
    }

    public IFuture postAsync(Runnable r) {
        return ((BNNetwork)this.getNetwork()).postAsync(r);
    }

    protected IFuture postPing() {
        return this.postAsync(new Invocation(this, ping, (BValue)null, (Context)null));
    }



    
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimised_buildings_ltd.kafka.BKafkaDevice(1159800966)1.0$ @*/
/* Generated Fri Jul 24 10:04:14 BST 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "message"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code message} property.
   * @see #getMessage
   * @see #setMessage
   */
  public static final Property message = newProperty(0, BString.DEFAULT, null);
  
  /**
   * Get the {@code message} property.
   * @see #message
   */
  public String getMessage() { return getString(message); }
  
  /**
   * Set the {@code message} property.
   * @see #message
   */
  public void setMessage(String v) { setString(message, v, null); }

////////////////////////////////////////////////////////////////
// Property "topic"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code topic} property.
   * @see #getTopic
   * @see #setTopic
   */
  public static final Property topic = newProperty(0, BString.DEFAULT, null);
  
  /**
   * Get the {@code topic} property.
   * @see #topic
   */
  public String getTopic() { return getString(topic); }
  
  /**
   * Set the {@code topic} property.
   * @see #topic
   */
  public void setTopic(String v) { setString(topic, v, null); }

////////////////////////////////////////////////////////////////
// Property "autocreate"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code autocreate} property.
   * @see #getAutocreate
   * @see #setAutocreate
   */
  public static final Property autocreate = newProperty(0, ((BBoolean)(BBoolean.FALSE)).getBoolean(), null);
  
  /**
   * Get the {@code autocreate} property.
   * @see #autocreate
   */
  public boolean getAutocreate() { return getBoolean(autocreate); }
  
  /**
   * Set the {@code autocreate} property.
   * @see #autocreate
   */
  public void setAutocreate(boolean v) { setBoolean(autocreate, v, null); }

////////////////////////////////////////////////////////////////
// Property "points"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code points} property.
   * @see #getPoints
   * @see #setPoints
   */
  public static final Property points = newProperty(0, new BKafkaPointDeviceExt(), null);
  
  /**
   * Get the {@code points} property.
   * @see #points
   */
  public BKafkaPointDeviceExt getPoints() { return (BKafkaPointDeviceExt)get(points); }
  
  /**
   * Set the {@code points} property.
   * @see #points
   */
  public void setPoints(BKafkaPointDeviceExt v) { set(points, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BKafkaDevice.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/




    


}
