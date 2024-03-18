import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import DashboardIcon from '@mui/icons-material/Dashboard';
import AddIcon from '@mui/icons-material/Add';
import {useNavigate} from "react-router-dom";

export default function NavList() {

    const navigate = useNavigate();


    return (
        <React.Fragment>
            <ListItemButton onClick={() => navigate("/")}>
                <ListItemIcon>
                    <DashboardIcon/>
                </ListItemIcon>
                <ListItemText primary="Dashboard"/>
            </ListItemButton>
            <ListItemButton onClick={() => navigate("/ticket")}>
                <ListItemIcon>
                    <AddIcon/>
                </ListItemIcon>
                <ListItemText primary="New Ticket"/>
            </ListItemButton>
        </React.Fragment>
    );
}