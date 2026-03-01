public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        Powerable pjPower = reg.getFirst(Projector.class); // Assuming projector specifically needed for HDMI later
        pjPower.powerOn();
        
        Connectable pjInput = reg.getFirst(Connectable.class);
        pjInput.connectInput("HDMI-1");

        Dimmable lights = reg.getFirst(Dimmable.class);
        lights.setBrightness(60);

        ClimateControllable ac = reg.getFirst(ClimateControllable.class);
        ac.setTemperatureC(24);

        Scanner scan = reg.getFirst(Scanner.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getFirst(Projector.class).powerOff();
        reg.getFirst(LightsPanel.class).powerOff();
        reg.getFirst(AirConditioner.class).powerOff();
    }
}
