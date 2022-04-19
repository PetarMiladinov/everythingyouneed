import logo from '../../logo.svg';
import React from 'react';
import './App.css';
import categories from "../Categories/categories";
import eynService from "../../Repository/eynRepository";
class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      categories : []
    }
  }

  loadCategories = () =>{
    eynService.fetchCategories()
        .then((data) =>{
          this.setState({
            categories: data.data
          })
        })
  }

  componentDidMount() {
      this.loadCategories();
  }

  render() {
    return (
      <div>
        <categories categories={this.state.categories}/>
      </div>
    );
  }
}

export default App;
