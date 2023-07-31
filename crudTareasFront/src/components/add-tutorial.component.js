import React, { Component } from "react";
import { connect } from "react-redux";
import { createTutorial } from "../slices/tutorials";
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";

class AddTutorial extends Component {

  constructor(props) {
    //cargo prop en padre
    super(props);

    //vinculo funciones a una instancia de componentes
    this.onChangeTitle = this.onChangeTitle.bind(this);
    this.onChangeDescription = this.onChangeDescription.bind(this);
    this.saveTutorial = this.saveTutorial.bind(this);
    this.newTutorial = this.newTutorial.bind(this);

    //Se setean estados iniciales
    this.state = {
      id: null,
      title: "",
      description: "",
      published: false,
      submitted: false,
    };
  }

  //cambia el estado de titulo
  onChangeTitle(e) {
    this.setState({
      title: e.target.value,
    });
  }

  //Cambia el estado de descripcion
  onChangeDescription(e) {
    this.setState({
      description: e.target.value,
    });
  }

  saveTutorial() {
    const { title, description } = this.state;

    this.props
      .createTutorial({ title, description })
      .unwrap()
      .then((data) => {
        this.setState({
          id: data.id,
          title: data.title,
          description: data.description,
          published: data.published,
          submitted: true,
        });
        console.log(data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  newTutorial() {
    this.setState({
      id: null,
      title: "",
      description: "",
      published: false,
      submitted: false,
    });
  }

  render() {
    return (
      <div className="submit-form">
        {this.state.submitted ? (
          <div>
            <h4>Tarea creada correctamente!</h4>
            {
              //<button className="btn btn-success" onClick={this.newTutorial}>
              //Crear Tarea.
            //</button>
        }
          </div>
        ) : (
          <div>
            <div className="form-group">
              <label htmlFor="description">Descripcion</label>
              <input
                type="text"
                className="form-control"
                id="description"
                required
                value={this.state.description}
                onChange={this.onChangeDescription}
                name="description"
              />
            </div>

            <div className="form-group">
              <label htmlFor="title">fecha de Creación (DD/MM/YY)</label>
              <input
                type="text"
                className="form-control"
                id="title"
                required
                value={this.state.title}
                onChange={this.onChangeTitle}
                name="title"
              />
            </div>

            <button onClick={this.saveTutorial} className="btn btn-success">
              Crear Tarea
            </button>
          </div>
        )}
      </div>
    );
  }
}

export default connect(null, { createTutorial })(AddTutorial);
