// let appointments = JSON.parse(localStorage.getItem('appointments')) || [];
// let schedules = JSON.parse(localStorage.getItem('schedules')) || [];

// // Function to register a new doctor
// function registerDoctor(name, specialization, email, phone) {

//     let doctor_id = 0;
//     if (doctors.length == 0) {
//         doctor_id = 1;
//     } else {
//         doctor_id = doctors[doctors.length - 1].id + 1;
//     }

//     const doctor = {
//         id: doctor_id,
//         name: name,
//         specialization: specialization,
//         email: email,
//         phone: phone
//     };

//     doctors.push(doctor);
//     console.log(doctors)
//     localStorage.setItem('doctors', JSON.stringify(doctors));
//     alert('Doctor registered successfully!');
//     location.reload();
// }

// // Function to display doctors in the list
// function displayDoctors() {
//     const doctorList = document.getElementById('doctorList');
//     console.log(doctors)
//     console.log(doctorList)
//     doctorList.innerHTML = '';

//     doctors.forEach(doctor => {
//         const doctorItem = document.createElement('li');
//         doctorItem.innerHTML = `
//             <h3>${doctor.name}</h3>
//             <br>
//             <p>Id: ${doctor.id}</p>
//             <p>Specialization: ${doctor.specialization}</p>
//             <p>Email: ${doctor.email}</p>
//             <p>Phone: ${doctor.phone}</p>
//         `;
//         doctorList.appendChild(doctorItem);
//     });
// }

// // Function to remove a doctor
// function removeDoctor(doctorId) {
//     doctors = doctors.filter(doctor => doctor.id != doctorId);
//     localStorage.setItem('doctors', JSON.stringify(doctors));

//     // Also remove the doctor's schedule and appointments
//     schedules = schedules.filter(schedule => schedule.doctorId !== doctorId);
//     appointments = appointments.filter(appointment => appointment.doctorId !== doctorId);
//     localStorage.setItem('schedules', JSON.stringify(schedules));
//     localStorage.setItem('appointments', JSON.stringify(appointments));

//     alert('Doctor removed successfully.');
//     location.reload();
// }


// // Function to enable editing of doctor details
// function updateDoctor(doctorId) {
//     const doctor = doctors.find(doc => doc.id == doctorId);
//     if (doctor) {
//         document.getElementById('doctorDetails').innerHTML = `
//             <div class="doctor-details">
//                 <h3><input type="text" id="editName" value="${doctor.name}"></h3>
//                 <p>Specialization: <input type="text" id="editSpecialization" value="${doctor.specialization}"></p>
//                 <p>Email: <input type="email" id="editEmail" value="${doctor.email}"></p>
//                 <p>Phone: <input type="text" id="editPhone" value="${doctor.phone}"></p>
//                 <div class="button-group">
//                     <button class="save-button" onclick="saveDoctor('${doctorId}')">Save</button>
//                     <button class="cancel-button" onclick="cancelUpdate('${doctorId}')">Cancel</button>
//                 </div>
//             </div>
//         `;
//     } else {
//         alert('Doctor not found');
//     }
// }

// // Function to save the updated doctor details
// function saveDoctor(doctorId) {
//     const doctorIndex = doctors.findIndex(doc => doc.id == doctorId);
//     if (doctorIndex !== -1) {
//         doctors[doctorIndex].name = document.getElementById('editName').value;
//         doctors[doctorIndex].specialization = document.getElementById('editSpecialization').value;
//         doctors[doctorIndex].email = document.getElementById('editEmail').value;
//         doctors[doctorIndex].phone = document.getElementById('editPhone').value;

//         localStorage.setItem('doctors', JSON.stringify(doctors));
//         alert('Doctor details updated successfully!');
//         location.reload();
//     } else {
//         alert('Error: Doctor not found.');
//     }
// }

// // Function to cancel the update and restore original details
// function cancelUpdate(doctorId) {
//     const doctor = doctors.find(doc => doc.id == doctorId);
//     if (doctor) {
//         document.getElementById('doctorDetails').innerHTML = `
//             <div class="doctor-details">
//                 <h3>${doctor.name}</h3>
//                 <p>Specialization: ${doctor.specialization}</p>
//                 <p>Email: ${doctor.email}</p>
//                 <p>Phone: ${doctor.phone}</p>
//                 <div class="button-group">
//                     <button class="update-button" onclick="updateDoctor('${doctorId}')">Update Doctor</button>
//                     <button class="remove-button" onclick="removeDoctor('${doctorId}')">Remove Doctor</button>
//                 </div>
//             </div>
//         `;
//     }
// }

// function updateDoctorScheduleView(doctorId) {
//     const doctor = doctors.find(doc => doc.id == doctorId);
//     const schedule = schedules.find(sch => sch.doctorId == doctorId);

//     if (doctor && schedule) {
//         document.getElementById('scheduleDetails').innerHTML = `
//             <div class="schedule-details">
//                 <h3>Schedule for Dr. ${doctor.name}</h3>
//                 <p>Working Hours: <input type="text" id="editWorkingHours" value="${schedule.workingHours}"></p>
//                 <p>Break Time: <input type="text" id="editBreakTime" value="${schedule.breakTime}"></p>
//                 <div class="button-group">
//                     <button class="save-button" onclick="saveSchedule('${doctorId}')">Save</button>
//                     <button class="cancel-button" onclick="cancelScheduleUpdate('${doctorId}')">Cancel</button>
//                 </div>
//             </div>
//         `;
//     } else {
//         alert('Doctor or schedule not found');
//     }
// }

// // Function to save the updated schedule
// function saveSchedule(doctorId) {
//     const scheduleIndex = schedules.findIndex(sch => sch.doctorId == doctorId);
//     if (scheduleIndex !== -1) {
//         schedules[scheduleIndex].workingHours = document.getElementById('editWorkingHours').value;
//         schedules[scheduleIndex].breakTime = document.getElementById('editBreakTime').value;

//         localStorage.setItem('schedules', JSON.stringify(schedules));
//         alert('Schedule updated successfully!');
//         location.reload();
//     } else {
//         alert('Error: Schedule not found.');
//     }
// }

// // Function to cancel the schedule update and restore original details
// function cancelScheduleUpdate(doctorId) {
//     const schedule = schedules.find(sch => sch.doctorId == doctorId);
//     if (schedule) {
//         document.getElementById('scheduleDetails').innerHTML = `
//             <div class="schedule-details">
//                 <h3>Schedule for Dr. ${doctor.name}</h3>
//                 <p>Working Hours: ${schedule.workingHours}</p>
//                 <p>Break Time: ${schedule.breakTime}</p>
//                 <div class="button-group">
//                     <button class="update-button" onclick="updateDoctorScheduleView('${doctorId}')">Update Schedule</button>
//                 </div>
//             </div>
//         `;
//     }
// }


// // Event listener for doctor registration form
// document.getElementById('registerDoctorForm').addEventListener('submit', function (event) {
//     event.preventDefault();
//     const name = document.getElementById('name').value;
//     const specialization = document.getElementById('specialization').value;
//     const email = document.getElementById('email').value;
//     const phone = document.getElementById('phone').value;
//     registerDoctor(name, specialization, email, phone);
// });

// // Event listener for page load to display doctors
// document.addEventListener('DOMContentLoaded', function () {
//     if (document.getElementById('doctorList')) {
//         displayDoctors();
//     }
// });

// Global variables
let doctors = JSON.parse(localStorage.getItem('doctors')) || [];
let appointments = JSON.parse(localStorage.getItem('appointments')) || [];
let schedules = JSON.parse(localStorage.getItem('schedules')) || [];

// Function to register a new doctor
function registerDoctor(name, specialization, email, phone) {
    let doctor_id = 0;
    if (doctors.length === 0) {
        doctor_id = 1;
    } else {
        doctor_id = doctors[doctors.length - 1].id + 1;
    }

    const doctor = {
        id: doctor_id,
        name: name,
        specialization: specialization,
        email: email,
        phone: phone
    };

    doctors.push(doctor);

    // Create a default schedule for the new doctor
    const defaultSchedule = {
        doctorId: doctor_id,
        workingHours: '09:00 to 17:00',
        breakTime: '13:00 to 14:00'
    };
    schedules.push(defaultSchedule);

    localStorage.setItem('doctors', JSON.stringify(doctors));
    localStorage.setItem('schedules', JSON.stringify(schedules));
    alert('Doctor registered successfully with default schedule!');
    location.reload();
}


// Function to display doctors in the list
function displayDoctors() {
    const doctorList = document.getElementById('doctorList');
    console.log(doctors);
    console.log(doctorList);
    doctorList.innerHTML = '';

    doctors.forEach(doctor => {
        const doctorItem = document.createElement('li');
        doctorItem.innerHTML = `
            <h3>${doctor.name}</h3>
            <br>
            <p>Id: ${doctor.id}</p>
            <p>Specialization: ${doctor.specialization}</p>
            <p>Email: ${doctor.email}</p>
            <p>Phone: ${doctor.phone}</p>
        `;
        doctorList.appendChild(doctorItem);
    });
}

// Function to remove a doctor
function removeDoctor(doctorId) {
    doctors = doctors.filter(doctor => doctor.id != doctorId);
    localStorage.setItem('doctors', JSON.stringify(doctors));

    // Also remove the doctor's schedule and appointments
    schedules = schedules.filter(schedule => schedule.doctorId !== doctorId);
    appointments = appointments.filter(appointment => appointment.doctorId !== doctorId);
    localStorage.setItem('schedules', JSON.stringify(schedules));
    localStorage.setItem('appointments', JSON.stringify(appointments));

    alert('Doctor removed successfully.');
    location.reload();
}

// Function to enable editing of doctor details
function updateDoctor(doctorId) {
    const doctor = doctors.find(doc => doc.id == doctorId);
    if (doctor) {
        document.getElementById('doctorDetails').innerHTML = `
            <div class="doctor-details">
                <h3><input type="text" id="editName" value="${doctor.name}"></h3>
                <p>Specialization: <input type="text" id="editSpecialization" value="${doctor.specialization}"></p>
                <p>Email: <input type="email" id="editEmail" value="${doctor.email}"></p>
                <p>Phone: <input type="text" id="editPhone" value="${doctor.phone}"></p>
                <div class="button-group">
                    <button class="save-button" onclick="saveDoctor('${doctorId}')">Save</button>
                    <button class="cancel-button" onclick="cancelUpdate('${doctorId}')">Cancel</button>
                </div>
            </div>
        `;
    } else {
        alert('Doctor not found');
    }
}

// Function to save the updated doctor details
function saveDoctor(doctorId) {
    const doctorIndex = doctors.findIndex(doc => doc.id == doctorId);
    if (doctorIndex !== -1) {
        doctors[doctorIndex].name = document.getElementById('editName').value;
        doctors[doctorIndex].specialization = document.getElementById('editSpecialization').value;
        doctors[doctorIndex].email = document.getElementById('editEmail').value;
        doctors[doctorIndex].phone = document.getElementById('editPhone').value;

        localStorage.setItem('doctors', JSON.stringify(doctors));
        alert('Doctor details updated successfully!');
        location.reload();
    } else {
        alert('Error: Doctor not found.');
    }
}

// Function to cancel the update and restore original details
function cancelUpdate(doctorId) {
    const doctor = doctors.find(doc => doc.id == doctorId);
    if (doctor) {
        document.getElementById('doctorDetails').innerHTML = `
            <div class="doctor-details">
                <h3>${doctor.name}</h3>
                <p>Specialization: ${doctor.specialization}</p>
                <p>Email: ${doctor.email}</p>
                <p>Phone: ${doctor.phone}</p>
                <div class="button-group">
                    <button class="update-button" onclick="updateDoctor('${doctorId}')">Update Doctor</button>
                    <button class="remove-button" onclick="removeDoctor('${doctorId}')">Remove Doctor</button>
                </div>
            </div>
        `;
    }
}

// Function to show schedule details with an "Update" button
function updateDoctorScheduleView(doctorId) {
    const doctor = doctors.find(doc => doc.id == doctorId);
    const schedule = schedules.find(sch => sch.doctorId == doctorId);

    if (doctor && schedule) {
        document.getElementById('scheduleDetails').innerHTML = `
            <div class="schedule-details">
                <h3>Schedule for Dr. ${doctor.name}</h3><br>
                <p>Working Hours: 
                    <span id="displayWorkingHours">${schedule.workingHours}</span>
                    <input type="time" id="editWorkingHoursStart" value="${schedule.workingHours.split(' to ')[0]}" style="display: none;">
                    <input type="time" id="editWorkingHoursEnd" value="${schedule.workingHours.split(' to ')[1]}" style="display: none;">
                </p>
                <p>Break Time: 
                    <span id="displayBreakTime">${schedule.breakTime}</span>
                    <input type="time" id="editBreakStart" value="${schedule.breakTime.split(' to ')[0]}" style="display: none;">
                    <input type="time" id="editBreakEnd" value="${schedule.breakTime.split(' to ')[1]}" style="display: none;">
                </p>
                <br>
                <button class="update-button" onclick="editSchedule('${doctorId}')">Update</button>
                <div class="button-group">
                    <button class="save-button" onclick="saveSchedule('${doctorId}')" style="display: none;">Save</button>
                    <button class="cancel-button" onclick="cancelScheduleUpdate('${doctorId}')" style="display: none;">Cancel</button>
                </div>
            </div>
        `;
    } else {
        alert('Doctor or schedule not found');
    }
}


// Function to switch to editable mode
function editSchedule(doctorId) {
    document.getElementById('editWorkingHoursStart').style.display = 'inline';
    document.getElementById('editWorkingHoursEnd').style.display = 'inline';
    document.getElementById('editBreakStart').style.display = 'inline';
    document.getElementById('editBreakEnd').style.display = 'inline';
    document.querySelector('.save-button').style.display = 'inline';
    document.querySelector('.cancel-button').style.display = 'inline';

    document.getElementById('displayWorkingHours').style.display = 'none';
    document.getElementById('displayBreakTime').style.display = 'none';
    document.querySelector('.update-button').style.display = 'none';
}


// Function to save the updated schedule
function saveSchedule(doctorId) {
    const scheduleIndex = schedules.findIndex(sch => sch.doctorId == doctorId);
    if (scheduleIndex !== -1) {
        const startWorkingHours = document.getElementById('editWorkingHoursStart').value;
        const endWorkingHours = document.getElementById('editWorkingHoursEnd').value;
        const startBreakTime = document.getElementById('editBreakStart').value;
        const endBreakTime = document.getElementById('editBreakEnd').value;

        schedules[scheduleIndex].workingHours = `${startWorkingHours} to ${endWorkingHours}`;
        schedules[scheduleIndex].breakTime = `${startBreakTime} to ${endBreakTime}`;

        localStorage.setItem('schedules', JSON.stringify(schedules));
        alert('Schedule updated successfully!');
        location.reload();
    } else {
        alert('Error: Schedule not found.');
    }
}

// Function to cancel the schedule update
function cancelScheduleUpdate(doctorId) {
    updateDoctorScheduleView(doctorId); // Restore the original view
}


// Event listener for doctor registration form
document.getElementById('registerDoctorForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const name = document.getElementById('name').value;
    const specialization = document.getElementById('specialization').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;
    registerDoctor(name, specialization, email, phone);
});

// Event listener for page load to display doctors
document.addEventListener('DOMContentLoaded', function () {
    if (document.getElementById('doctorList')) {
        displayDoctors();
    }
});
