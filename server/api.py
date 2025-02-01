from flask import Flask, request, jsonify

app = Flask(__name__)

# API за свързване към сървър
@app.route('/connect', methods=['GET'])
def connect():
    server = request.args.get('server')
    if not server:
        return jsonify({"error": "Server not specified"}), 400

    # Симулиране на връзка
    response = {
        "message": f"Successfully connected to server: {server}",
        "ping": "20ms",
        "download": "100Mbps",
        "upload": "50Mbps"
    }
    return jsonify(response)

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
