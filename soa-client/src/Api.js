class Api {

  constructor(authToken) {
    this.authToken = authToken;
  }

  headers = {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  };

  STUDENTS_BASE_URL = 'http://localhost:8080/students';
  GRADES_BASE_URL = 'http://localhost:8080/grades';

  createHeaders() {
    return this.authToken ? {
      ...this.headers,
      'Authorization': 'Bearer ' + this.authToken
    } : this.headers;
  }

  async getStudents() {
    return await fetch(this.STUDENTS_BASE_URL, {
      method: 'GET',
      headers: this.createHeaders()
    });
  }

  async delete(id) {
    return await fetch(`${this.STUDENTS_BASE_URL}/${id}`, {
      method: 'DELETE',
      headers: this.createHeaders()
    });
  }


  async createStudent(student) {
    return await fetch(this.STUDENTS_BASE_URL, {
      method: 'POST',
      headers: this.createHeaders(),
      body: JSON.stringify(student)
    });
  }

  async getGrades() {
    return await fetch(this.GRADES_BASE_URL, {
      method: 'GET',
      headers: this.createHeaders()
    });
  }

  async createGrade(grade) {
    return await fetch(this.STUDENTS_BASE_URL, {
      method: 'POST',
      headers: this.createHeaders(),
      body: JSON.stringify(grade)
    });
  }

}

export default Api;