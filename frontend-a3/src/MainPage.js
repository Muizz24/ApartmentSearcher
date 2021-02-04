/* jshint esversion: 6 */
import React, { Component } from 'react';
import './style/mainPage.css'
import { Redirect } from 'react-router-dom';
import { beginSearch } from './api';

class Main extends Component {
    constructor(props) {
        super(props);
        this.state = { signup: true };
        this.handleSwitch = this.handleSwitch.bind(this);
    }

    // Switch between signup and signin forms
    handleSwitch(e) {
        this.setState({ signup: !(this.state.signup)})
    }

    render() {
        if (this.state.signup) {
            return (
                <div>
                    <header id="title" >
                        Kijiji Rentals
                    </header>
                    <div className="align_center">
                        <Search handleSwitch={this.handleSwitch} />
                    </div>
                </div>
            );
        } else {
            return (
                <div>
                    <header id="title" >
                        Kijiji Rentals
                    </header>
                    <div className="align_center">
                        <Search handleSwitch={this.handleSwitch} />
                    </div>
                </div>
            );
        }
    }
}

// https://reactjs.org/docs/forms.html
class Search extends Component {
    constructor(props) {
        super(props);
        this.state = { seed: '', limit: ''};
        this.handleSeed = this.handleSeed.bind(this);
        this.handleLimit = this.handleLimit.bind(this);
        this.handleSearch = this.handleSearch.bind(this);
    }

    handleSeed(e) {
        this.setState({ seed: e.target.value });
    }

    handleLimit(e) {
        this.setState({ limit: e.target.value });
    }

    handleSearch(e) {
        beginSearch(this.state.seed, this.state.limit);
        this.setState({ seed: '', limit: '' });
    }

    enterKey = (e) => {
        if (e.key === 'Enter' && this.state.seed !== '' && this.state.limit !== '') {
            this.handleSearch(e);
        }
    }

    // Method from search class
    handleSwitch = (e) => {
        this.props.handleSwitch(e);
    }

    render() {
        return (
            <form className="login_form">
                <p className="login_title">
                    Find Rental Locations FAST!
                </p>
                <input type="text" placeholder="Seed" className="login_element"  value={this.state.seed} onChange={this.handleSeed} />
                <input type="text" placeholder="Limit" className="login_element" value={this.state.limit} onChange={this.handleLimit} onKeyPress={this.enterKey} />
                <button type="button" className="login_btn" onClick={this.handleSearch}>Search</button>
            </form>
        );
    }
}

export default Search;