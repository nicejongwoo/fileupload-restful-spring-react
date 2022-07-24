import {useState} from "react";

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
        //post attach
        fetch("http://localhost:8080/api/attachment", {
            method: "POST",
            body: JSON.stringify({"fileId": fileId})
        }).then((response) => response.json()).then((result) => {

        }).catch((error) => {
            console.error("Error: ", error);
        })
    }

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