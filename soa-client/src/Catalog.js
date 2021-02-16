import React, { Component } from 'react';
import { withOktaAuth } from '@okta/okta-react';
import Api from './Api';

export default withOktaAuth(class Catalog extends Component {
  constructor(props) {
    super(props)
    const accessToken = this.props.authState.accessToken
    this.state = {
      api: new Api(accessToken),
      students: [],
      errorMessage: null,
    }
  }

  async componentDidMount() {
    const response = await this.state.api.getStudents();
    if (response.ok) {
      const students = await response.json();
      console.log(students)
      this.setState({students: students})
    } else {
      this.setState({errorMessage: "Failed to load students."})
    }
  }

  render() {
    const items = this.state.students.map(student =>
      <li key={student.id}>{student.name}</li>
    );
    return <ul>{items}</ul>;
  }
});