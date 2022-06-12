const int BTN = 2;
const int LED = 3;

volatile int estado = LOW;

void setup() {
  Serial.begin(9600);
  pinMode(BTN, INPUT);
  pinMode(LED, OUTPUT);
  attachInterrupt(digitalPinToInterrupt(BTN), paroEmergencia, CHANGE);
}

void paroEmergencia() {
  estado = !estado;
  Serial.println("¡ALERTA! ACCIDENTE REPORTADO");
}

void loop() {
  digitalWrite(LED, estado);
}
