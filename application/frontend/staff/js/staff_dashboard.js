document.addEventListener("DOMContentLoaded", function () {
  // Register Patient Form
  if (document.getElementById("registerPatientForm")) {
    document
      .getElementById("registerPatientForm")
      .addEventListener("submit", function (event) {
        event.preventDefault();
        const patientId = document.getElementById("patientId").value;
        const patientName = document.getElementById("patientName").value;
        const patientEmail = document.getElementById("patientEmail").value;
        const patientPhone = document.getElementById("patientPhone").value;
        registerPatient(patientId, patientName, patientEmail, patientPhone);
      });
  }

  // Book Appointment Form
  if (document.getElementById("bookAppointmentForm")) {
    document
      .getElementById("bookAppointmentForm")
      .addEventListener("submit", function (event) {
        event.preventDefault();
        const patientId = document.getElementById("patientId").value;
        const patientName = document.getElementById("patientName").value;
        const doctorId = document.getElementById("doctorId").value;
        const doctorName = document.getElementById("doctorName").value;
        const appointmentDate =
          document.getElementById("appointmentDate").value;
        const appointmentTime =
          document.getElementById("appointmentTime").value;
        bookAppointment(
          patientId,
          patientName,
          doctorId,
          doctorName,
          appointmentDate,
          appointmentTime
        );
      });

    // Add blur event listeners for Patient ID and Doctor ID fields
    document.getElementById("patientId").addEventListener("blur", function () {
      const patientId = this.value;
      const patientNameField = document.getElementById("patientName");
      const patientName = getPatientNameById(patientId);
      if (patientName) {
        patientNameField.value = patientName;
      } else {
        alert("Patient ID not found.");
        patientNameField.value = "";
      }
    });

    document.getElementById("doctorId").addEventListener("blur", function () {
      const doctorId = this.value;
      const doctorNameField = document.getElementById("doctorName");
      const doctorName = getDoctorNameById(doctorId);

      if (doctorName) {
        doctorNameField.value = doctorName;
      } else {
        alert("Doctor ID not found.");
        doctorNameField.value = "";
      }
    });
  }

  // Load appointments if present
  loadAppointments();
});

// Functions to save and load data from LocalStorage

function registerPatient(patientId, patientName, patientEmail, patientPhone) {
  let patients = JSON.parse(localStorage.getItem("patients")) || [];
  patients.push({ patientId, patientName, patientEmail, patientPhone });
  localStorage.setItem("patients", JSON.stringify(patients));
  alert("Patient registered successfully!");
  console.log(JSON.parse(localStorage.getItem("patients")));
}

function bookAppointment(
  patientId,
  patientName,
  doctorId,
  doctorName,
  appointmentDate,
  appointmentTime
) {
  let appointments = JSON.parse(localStorage.getItem("appointments")) || [];
  appointments.push({
    app_id: appointments.length
      ? appointments[appointments.length - 1].id + 1
      : 1,
    patientId,
    patientName,
    doctorId,
    doctorName,
    appointmentDate,
    appointmentTime,
  });
  localStorage.setItem("appointments", JSON.stringify(appointments));
  alert("Appointment booked successfully!");
}

function getPatientNameById(patientId) {
  const patients = JSON.parse(localStorage.getItem("patients")) || [];
  const patient = patients.find((p) => p.patientId === patientId);
  return patient ? patient.patientName : null;
}

function getDoctorNameById(doctorId) {
  const doctors = JSON.parse(localStorage.getItem("doctors")) || [];
  console.log(`doctorID searched is ${doctorId}`);
  console.log(doctors);

  const doctor = doctors.find((d) => d.doctorId == doctorId);

  return doctor ? doctor.doctorName : null;
}
function loadAppointments() {
  const appointmentList = document.getElementById("appointmentList");
  if (appointmentList) {
    let appointments = JSON.parse(localStorage.getItem("appointments")) || [];
    appointments.forEach((appointment) => {
      let li = document.createElement("li");
      li.textContent = `Appointment for Patient ID: ${appointment.patientId} with Doctor ID: ${appointment.doctorId} on ${appointment.appointmentDate}`;
      appointmentList.appendChild(li);
    });
  }
}
