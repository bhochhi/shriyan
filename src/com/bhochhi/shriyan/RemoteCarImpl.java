package com.bhochhi.shriyan;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


@ManagedBean(name="remoteCar")
@SessionScoped
public class RemoteCarImpl implements RemoteCar, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String response;
	private GpioController gpio;
	private GpioPinDigitalOutput thrustPin12F;
	private GpioPinDigitalOutput thrustPin13B;
	private GpioPinDigitalOutput steeringPin0Right;
	private GpioPinDigitalOutput steeringPin2Left;
	private GpioPinDigitalOutput frontLight;
	private GpioPinDigitalOutput backLight;
	private Timer steeringTimer = null;
    private Timer thrustTimer = null;
    private static final int COMMAND_TIMEOUT = 300;
	// Commands to control thrust ie. forward and backward
    public enum ThrustCommand {
            OFF, FORWARD, BACKWARD
    }

    // Command to control steering
    public enum SteeringCommand {
            OFF, LEFT, RIGHT
    }

	public RemoteCarImpl(){
		try{
			 gpio = GpioFactory.getInstance();

             thrustPin12F = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12,
                             "ThrustPin0F", PinState.LOW);
             thrustPin13B = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13,
                             "thrustPin13B", PinState.LOW);
             steeringPin0Right = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00,
                             "steeringPin0L", PinState.LOW);
             steeringPin2Left = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02,
                             "steeringPin2R", PinState.LOW);
             frontLight = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07,
                             "frontLight", PinState.LOW);
             backLight = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,
                             "backLight", PinState.LOW);
		}catch(Exception e){
			e.printStackTrace();
			}
	}
	
	@Override
	public void moveLeft() {
		System.out.println("Moved Left");
		   steeringPin0Right.low();
           steeringPin2Left.high();
           steeringTimer.schedule(new TimerTask() {
                   @Override
                   public void run() {
                           System.out.println("command timed out");
                           steeringPin2Left.low();
                   }
           }, COMMAND_TIMEOUT);
		setResponse("Moved Left");
		
	}

	@Override
	public void moveRight() {
		System.out.println("Moved Right");
		setResponse("Moved Right");
	}

	@Override
	public void moveForward() {
		System.out.println("Moved Forward");
		setResponse("Moved Forward");
	}

	@Override
	public void moveReverse() {
		System.out.println("Move Reverse");
		setResponse("Moved Reverse");
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
