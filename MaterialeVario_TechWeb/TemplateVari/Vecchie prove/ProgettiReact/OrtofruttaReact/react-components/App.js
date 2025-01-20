"use strict";

class App extends React.Component {
    /*** Costruttore ********************************************************************/
    constructor() {
        super();
        // Nomi delle sezioni
        this.sectionNames = ["Compra", "Carrello"];

        // Colori da estrarre
        this.colors = ["blue", "red", "yellow", "green", "brown", "black", "grey", "lightBlue"];

        // Genero un array di tre colori
        this.sectionColors = Array.from({ length: 3 }, (_) => this.colors[Math.floor(Math.random() * this.colors.length)])

        // Riestraggo finchè tutti i colori non sono diversi
        while (this.sectionColors.some((item, index) => this.sectionColors.indexOf(item) !== index)) {
            this.sectionColors = Array.from({ length: 3 }, (_) => this.colors[Math.floor(Math.random() * this.colors.length)]);
        }

        this.startingList = '{"items":[{"name":"pescetto", "qt":"50g", "type": "cp"}, {"name":"pescetto", "qt":"50g", "type": "cp"}, {"name":"verdurine", "qt":"500g", "type": "fv"}]}'

        // Inizializzazione dello state
        this.state = {
            compra: JSON.parse(this.startingList).items,
            carrello: [],
            colors: this.sectionColors
        };
        
        // Binding delle funzioni
        this.addItem = this.addItem.bind(this);
        this.switch1 = this.switch1.bind(this);
        this.switch2 = this.switch2.bind(this);
    }

    /*** Funzioni ***********************************************************************/
    // Aggiungi alimento
    addItem() {
        let name = document.getElementById("nome").value;
        let qt = document.getElementById("qt").value;
        let type = document.getElementById("type").value;
        if (name && qt) {
            this.setState({ compra: [...this.state.compra, { name: name, qt: qt, type: type }] })
            document.getElementById("nome").value = "";
            document.getElementById("qt").value = "";
        }
    }

    switch1(e) {
        let element = e.target.name;
        let newList = this.state.compra;
        let idx = newList.map(item => JSON.stringify(item)).indexOf(element);
        newList.splice(idx, 1);
        this.setState({
            compra: newList,
            carrello: [...this.state.carrello, JSON.parse(element)]
        })
    }

    switch2(e) {
        let element = e.target.name;
        let newList = this.state.carrello;
        let idx = newList.map(item => JSON.stringify(item)).indexOf(element);
        newList.splice(idx, 1)
        this.setState({
            carrello: newList,
            compra: [...this.state.compra, JSON.parse(element)]
        })
    }



    /*** Render *************************************************************************/
    render() {
        return (
            <div>
                <Riquadro
                    sectionName={this.sectionNames[0]}
                    colors={this.state.colors}
                    items={this.state.compra}
                    switch={this.switch1}
                />
                <div style={{ padding: "10px" }}>

                    <b>Aggiungi alimento</b>
                    <br></br>
                    <br></br>
                    <select name="type" id="type" style={{ width: "150px", height: "35px" }}>
                        <option value="fv">Frutta e verdura</option>
                        <option value="cp">Carne e pesce</option>
                        <option value="lc">Lunga conservazione</option>
                    </select>
                    <input id="nome" type="text" placeholder="Nome" style={{ width: "150px", height: "30px" }}></input>
                    <input id="qt" type="text" placeholder="Quantità" style={{ width: "150px", height: "30px" }}></input>
                    <br></br>
                    <br></br>
                    <button onClick={this.addItem}>Aggiungi!</button>
                </div>

                <Riquadro
                    sectionName={this.sectionNames[1]}
                    colors={this.state.colors}
                    items={this.state.carrello}
                    switch={this.switch2}
                />
            </div>
        );
    }
}
