import {
    Button,
    ButtonGroup,
    FormControl, FormGroup, ListItem, Tab, Tabs,
    TextField
} from "@mui/material";
import LoadingButton from '@mui/lab/LoadingButton';
import React, {ChangeEvent, KeyboardEventHandler, useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {generateClient} from "aws-amplify/api";
import {Ticket, TicketCreateInput, TicketUpdateInput} from "../API";
import {getTicketById} from "../graphql/queries";
import Box from "@mui/material/Box";
import status from "../utils/status";
import PriorityIcon from "./PriorityIcon";
import CategoryIcon from "./CategoryIcon";
import {createComment, createTicket, updateTicket} from "../graphql/mutations";
import Typography from "@mui/material/Typography";
import List from "@mui/material/List";
import ListItemText from "@mui/material/ListItemText";
import renderDate from "../utils/render-date";

interface TabPanelProps {
    children?: React.ReactNode;
    index: number;
    value: number;
}

function CustomTabPanel(props: TabPanelProps) {
    const {children, value, index, ...other} = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`simple-tabpanel-${index}`}
            aria-labelledby={`simple-tab-${index}`}
            {...other}
        >
            {value === index && (
                <Box sx={{p: 3}}>
                    <Typography>{children}</Typography>
                </Box>
            )}
        </div>
    );
}

function a11yProps(index: number) {
    return {
        id: `simple-tab-${index}`,
        'aria-controls': `simple-tabpanel-${index}`,
    };
}

export default function TicketComponent() {
    const params = useParams();
    const client = generateClient();
    const [ticket, setTicket] = useState<Ticket>()
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const [tabIndex, setTabIndex] = React.useState(0);
    const handleChange = (event: React.SyntheticEvent, newValue: number) => {
        setTabIndex(newValue);
    };
    const getTicket = async (ticketId: number) => {
        const result = await client.graphql({
            query: getTicketById,
            variables: {id: ticketId}
        });
        // @ts-ignore
        setTicket(result.data.getTicketById[0])
    }

    const saveTicket = async () => {
        if (ticket != null) {
            if (ticket.id === 0) {
                const ticketCreate: TicketCreateInput = {
                    category_id: ticket.category_id,
                    content: ticket.content,
                    created_at: "",
                    priority_id: ticket.priority_id,
                    status_id: ticket.status_id,
                    updated_at: "",
                    user_id: ticket.user_id,
                    subject: ticket.subject
                }
                await client.graphql({
                    query: createTicket,
                    variables: {input: ticketCreate}
                })
            } else {
                const ticketInput: TicketUpdateInput = {
                    id: ticket.id,
                    subject: ticket.subject,
                    content: ticket.content,
                    status_id: ticket.status_id,
                    priority_id: ticket.priority_id,
                    user_id: ticket.user_id,
                    category_id: ticket.category_id,
                    created_at: ticket.created_at,
                    updated_at: ticket.updated_at,
                    completed_at: ticket.completed_at,
                }
                await client.graphql({
                    query: updateTicket,
                    variables: {input: ticketInput}
                });
            }
        }
    }

    useEffect(() => {
        if (params.id !== undefined) {
            const id = +(params.id)
            getTicket(id);
        } else {
            setTicket({
                subject: "",
                content: "",
                status_id: 1,
                priority_id: 1,
                user_id: 1,
                category_id: 1,
                __typename: "Ticket",
                audits: undefined,
                comments: undefined,
                completed_at: undefined,
                created_at: "",
                id: 0,
                updated_at: ""
            });
        }
    }, [params])

    const cancel = () => {
        navigate("/");
    }

    const save = () => {
        setLoading(true)
        saveTicket().then(() => {
            setLoading(false);
            navigate("/")
        });
    }

    const changeCategory = () => {
        if (ticket != null) {
            var category = ticket.category_id < 3 ? ticket.category_id + 1 : 1;
            setTicket({
                ...ticket, category_id: category
            });
        }
    };

    const changeStatus = () => {
        if (ticket != null) {
            var status = ticket.status_id < 3 ? ticket.status_id + 1 : 2;
            setTicket({
                ...ticket, status_id: status
            });
        }
    }

    const changePriority = () => {
        if (ticket != null) {
            var priority = ticket.priority_id < 3 ? ticket.priority_id + 1 : 1;
            setTicket({
                ...ticket, priority_id: priority
            });
        }
    }

    const changeSubject = (event: ChangeEvent<HTMLInputElement>) => {
        if (ticket != null) {
            setTicket({
                ...ticket, subject: event.target.value
            });
        }
    }

    const changeContent = (event: ChangeEvent<HTMLInputElement>) => {
        if (ticket != null) {
            setTicket({
                ...ticket, content: event.target.value
            });
        }
    }

    const addComment = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if (event.key == "Enter" && ticket !== undefined) {
            // @ts-ignore
            const content = event.target.value;
            client.graphql({
                query: createComment,
                variables: {
                    input: {
                        content: content,
                        user_id: 1,
                        ticket_id: ticket.id || 0,
                        created_at: "",
                        updated_at: ""
                    }
                }
            }).then(_ => getTicket(ticket.id));
        }
    }

    function CommentsPanel() {
        if (ticket !== null && ticket?.id !== 0) {
            return <CustomTabPanel value={tabIndex} index={1}>
                <TextField onKeyDown={addComment} label={"Add Comment"} sx={{width: '100%'}}></TextField>
                <List sx={{width: '100%', minHeight:'50ch', bgcolor: 'background.paper'}}>
                    {ticket?.comments?.map(c =>
                        <ListItem alignItems="flex-start">
                            <ListItemText
                                primary={c?.content}
                                secondary={renderDate(c?.created_at)}
                            />
                        </ListItem>)}
                </List>
            </CustomTabPanel>
        } else return <></>
    }

    if (ticket == null) return <></>

    return (
        <Box
            component="form"
            noValidate
            autoComplete="off"
        >
            <FormControl>
                <FormGroup sx={{m: 1, p: '16.5px 14px'}}>
                    <ButtonGroup variant="outlined">
                        <Button onClick={changeStatus}>{status(ticket?.status_id)}</Button>
                        <Button onClick={changePriority}><PriorityIcon id={ticket?.priority_id}/></Button>
                        <Button onClick={changeCategory}><CategoryIcon id={ticket?.category_id}/></Button>
                    </ButtonGroup>
                </FormGroup>
                <TextField defaultValue={ticket?.subject} label={"Subject"} inputProps={{shrink: true}}
                           onChange={changeSubject} sx={{m: 1, p: '16.5px 14px'}}></TextField>
                <Box sx={{borderBottom: 1, borderColor: 'divider', width: '75ch'}}>
                    <Tabs value={tabIndex} onChange={handleChange} aria-label="basic tabs example">
                        <Tab label="Detail" {...a11yProps(0)} />
                        {ticket?.id !== 0 && <Tab label="Comments" {...a11yProps(1)} />}
                    </Tabs>
                </Box>
                <CustomTabPanel value={tabIndex} index={0}>
                    <TextField multiline={true} minRows={20} defaultValue={ticket?.content} label={"Content"}
                               inputProps={{shrink: true}} onChange={changeContent} sx={{width: '100%'}}
                    ></TextField>
                </CustomTabPanel>
                <CommentsPanel/>
                <ButtonGroup sx={{m: 1, p: '16.5px 14px'}}>
                    <Button variant="outlined" onClick={cancel}>Cancel</Button>
                    <LoadingButton variant="contained" onClick={save} loading={loading}>Save</LoadingButton>
                </ButtonGroup>
            </FormControl>
        </Box>
    );
}