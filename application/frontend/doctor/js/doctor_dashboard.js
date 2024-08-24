document.addEventListener("DOMContentLoaded", function () {
  // Schedule Form
  if (document.getElementById("scheduleForm")) {
    document
      .getElementById("scheduleForm")
      .addEventListener("submit", function (event) {
        event.preventDefault();
        const doctorId = document.getElementById("doctorId").value;
        const workingHoursStart = document.getElementById("workingHoursStart").value;
        const workingHoursEnd = document.getElementById("workingHoursEnd").value;
        const breakTimeStart = document.getElementById("breakTimeStart").value;
        const breakTimeEnd = document.getElementById("breakTimeEnd").value;
        saveSchedule(doctorId, workingHoursStart, workingHoursEnd, breakTimeStart, breakTimeEnd);
      });
  }

  // Test Form
  if (document.getElementById("testForm")) {
    document
      .getElementById("testForm")
      .addEventListener("submit", function (event) {
        event.preventDefault();
        const patientId = document.getElementById("patientId").value;
        const testName = document.getElementById("testName").value;
        suggestTest(patientId, testName);
      });
  }

  // Medication Form
  if (document.getElementById("medicationForm")) {
    document
      .getElementById("medicationForm")
      .addEventListener("submit", function (event) {
        event.preventDefault();
        const patientId = document.getElementById("patientId").value;
        const medicationName = document.getElementById("medicationName").value;
        suggestMedication(patientId, medicationName);
      });
  }

  // Load appointments and notifications if present
  loadAppointments();
});

// Functions to save and load data from LocalStorage

function saveSchedule(doctorId, workingHoursStart, workingHoursEnd, breakTimeStart, breakTimeEnd) {
  let schedules = JSON.parse(localStorage.getItem("schedules")) || [];
  schedules.push({ doctorId, workingHoursStart, workingHoursEnd, breakTimeStart, breakTimeEnd });
  localStorage.setItem("schedules", JSON.stringify(schedules));
  alert("Schedule saved successfully!");
  console.log(JSON.parse(localStorage.getItem("schedules")));
}

function suggestTest(patientId, testName) {
  let tests = JSON.parse(localStorage.getItem("tests")) || [];
  tests.push({ patientId, testName });
  localStorage.setItem("tests", JSON.stringify(tests));
  alert("Test suggested successfully!");
  console.log(JSON.parse(localStorage.getItem("tests")));
}

function suggestMedication(patientId, medicationName) {
  let medications = JSON.parse(localStorage.getItem("medications")) || [];
  medications.push({ patientId, medicationName });
  localStorage.setItem("medications", JSON.stringify(medications));
  alert("Medication suggested successfully!");
  console.log(JSON.parse(localStorage.getItem("medications")));
}

function loadAppointments() {
  const appointmentList = document.getElementById("appointmentList");
  if (appointmentList) {
    let appointments = JSON.parse(localStorage.getItem("appointments")) || [];
    appointments.forEach((appointment) => {
      let li = document.createElement("li");
      li.textContent = `Appointment with Patient ID: ${appointment.patientId} on ${appointment.date}`;
      appointmentList.appendChild(li);
    });
  }
}
