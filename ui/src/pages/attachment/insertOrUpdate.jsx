import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";

function AttachmentInsertOrUpdate () {

    const [fileId, setFileId] = useState("");
    const [preview, setPreview] = useState("");

    const uploadFileApi = (file) => {
        const formData = new FormData();
        formData.append("file", file)
        fetch("http://localhost:8080/api/file/upload", {
            method: "POST",
            body: formData,
        }).then((response) => response.json()).then((result) => {
            console.log("Success:: ", result)
            //set fileId
            setFileId(result);
            //set preview
            setPreview(URL.createObjectURL(file));
        }).catch((error) => {
            console.error("Error:: ", error)
        })
    }

    const handleFileSelect = (e) => {
        //upload file
        const file = e.target.files[0]
        uploadFileApi(file)
    }

    const handleSubmit = () => {
        console.log("fileId:: ", fileId)
        //post attach
        fetch("http://localhost:8080/api/attachment", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({"fileId": fileId})
        }).then((response) => response.json()).then((result) => {
            console.log("Result:: ", result);
        }).catch((error) => {
            console.error("Error: ", error);
        })
    }
    const params = useParams()
    const id = params.id;

    const downloadFile = (id) => {
        fetch(`http://localhost:8080/api/file/download/${id}`, {
            method: "GET",
        }).then(response => response.blob()).then(result => {
            console.log("downloadFile Success::: ", result)
            setPreview(URL.createObjectURL(result));
        }).catch(error => {
            console.error("downloadFile Error:: ", error)
        })
    }

    const getFileByAttachmentId = (id) => {
        fetch(`http://localhost:8080/api/attachment/${id}`, {
            method: "GET",
        }).then(response => response.json()).then(result => {
            console.log("getFileByAttachmentId Success::: ", result);
            downloadFile(result.file.id)
            setFileId(result.file.id)
        }).catch(error => {
            console.error("getFileByAttachmentId Error::: ", error);
        })
    }
    useEffect(() => {
        console.log("id:: ", id)
        if (id !== undefined) {
            //get file
            //then download -> preview
            getFileByAttachmentId(id)
        }
    }, [id])

    return (
        <>
            <div>
                {/*<input*/}
                {/*    type="hidden"*/}
                {/*    value={fileId}*/}
                {/*/>*/}
                <button
                    onClick={handleSubmit}
                >
                    Submit
                </button>
            </div>
            <br/>

            <div>
                <label className="text-white">Select File</label>
                <input
                    type="file"
                    className="form-control"
                    onChange={handleFileSelect}
                />
            </div>
            <br/>

            {preview && <img className="previewImage" src={preview} align="uploadImage" /> }
        </>
    )
}

export default AttachmentInsertOrUpdate;