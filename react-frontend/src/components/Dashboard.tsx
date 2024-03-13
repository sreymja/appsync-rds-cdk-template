import * as React from 'react';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Tickets from "./Tickets";

export default function Dashboard() {

    return (
        <Grid container spacing={3}>
            {/* Tickets */}
            <Grid item xs={12}>
                <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
                    <Tickets />
                </Paper>
            </Grid>
        </Grid>
    );
}
