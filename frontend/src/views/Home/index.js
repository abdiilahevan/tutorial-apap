import React from 'react';
import listItems from "../../items.json";
import List from "../../components/List/index";
import "./index.css";
import { Fab, Badge } from '@mui/material';
import ViewStreamIcon from '@mui/icons-material/ViewStream';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';

export default class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            shopItems: listItems,
            cartItems: [],
            cartHidden: true,
            balance: 120,
        };
    }
    render() {
        return (
            <div className = "container-fluid">
                <h1 className="text-center mt-3 mb-0">Mini Commerce</h1>
                <div style={{ position : "fixed", top: 25, right: 25 }}>
                    <Fab variant="extended" onClick = {this.handleToggle}>
                        {this.state.cartHidden ?
                            <Badge color = "secondary" badgeContent={this.state.cartItems.lengths}>
                                <ShoppingCartIcon />
                            </Badge>
                            : <ViewStreamIcon />}
                    </Fab>
                </div>
                <p className="text-center text-secondary text-sm font-italic">
                    (this is a <strong>class-based</strong> application)
                </p>
                <p className = "text-center text-primary" >Your Balance: <b>{this.state.balance}</b></p>
                <div className = "container pt-3">
                    <div className = "row mt-3">
                        {!this.state.cartHidden ? (
                            <div className = "col-sm">
                                <List  
                                    title="My Cart"
                                    items = {this.state.cartItems}
                                    onItemClick={this.deleteAllCart}
                                ></List>
                            </div>
                        ) : <div className="col-sm">
                            <List  
                                title = "List Items"
                                items = {this.state.shopItems}
                                onItemClick = {this.handleAddItemToCart}
                                isShopList = {true}
                            ></List>
                        </div>}
                    </div>
                </div>
            </div>
        );
    }
    //No 3
    handleAddItemToCart = (item) => {
        const newItems = [...this.state.cartItems];
        const newItem = {...item};
        const targetInd = newItems.findIndex((it) => it.id === newItem.id);
        if (this.state.balance > newItem.price){
            if (targetInd < 0) {
                newItem.inCart = true;
                newItems.push(newItem);
                this.updateShopItem(newItem, true)
            }
            this.setState({ cartItems: newItems , balance: (this.state.balance - newItem.price)});
        }else{
            alert("Gacukup gan");
        }
    };

    //soal 1 & 2
    handleDeleteItemInCart = (item) => {
        const newItems = [...this.state.cartItems];
        const newItem = {...item};
        const deleteCart = newItems.findIndex((it) => it.id === newItem.id);
        if(deleteCart > -1){
            newItem.inCart = false;
            newItems.splice(deleteCart, 1);
            this.updateShopItem(newItem, false)
        }
        this.setState({ cartItems: newItems, balance: (this.state.balance + newItem.price) });
    }

    deleteAllCart = (item) => {
        var deleteCart1 = [...this.state.cartItems].length
        for (var i = 0 ; i < deleteCart1 ; i++){
            const newItems = [...this.state.cartItems];
            const newItem = {...item};
            const deleteCart = newItems.findIndex((it) => it.id === newItem.id);
            if(deleteCart > -1){
                newItem.inCart = false;
                newItems.splice(deleteCart, 1);
                this.updateShopItem(newItem, false)
            }
            this.setState({ cartItems: newItems, balance: (this.state.balance + newItem.price) });
        }

    }

    updateShopItem = (item,inCart) => {
        const tempShopItems = this.state.shopItems;
        const targetInd = tempShopItems.findIndex((it) => it.id === item.id);
        tempShopItems[targetInd].inCart = inCart;
        this.setState({ shopItems: tempShopItems });
    }

    handleToggle = () => {
        const cartHidden = this.state.cartHidden;
        this.setState({ cartHidden: !cartHidden });
    }
}